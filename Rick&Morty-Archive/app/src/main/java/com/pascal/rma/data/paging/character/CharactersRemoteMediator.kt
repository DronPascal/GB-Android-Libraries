package com.pascal.rma.data.paging.character

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.pascal.rma.data.cache.room.character.CharacterDatabase
import com.pascal.rma.data.cache.room.character.RoomCharacter
import com.pascal.rma.data.cache.room.character.RoomCharacterMappers.toRoomCharacter
import com.pascal.rma.data.remote.retrofit.character.CharacterApiService
import com.pascal.rma.data.remote.retrofit.character.model.ApiCharactersPage
import com.pascal.rma.util.apiId
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InvalidObjectException

/**
 * Created by dronpascal on 10.10.2021.
 */
@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val service: CharacterApiService,
    private val database: CharacterDatabase,
) : RxRemoteMediator<Int, RoomCharacter>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, RoomCharacter>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (it) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.prevKey ?: INVALID_PAGE
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                            ?: throw InvalidObjectException("Result is empty")
                        remoteKeys.nextKey ?: INVALID_PAGE
                    }
                    else -> throw InvalidObjectException("Result is empty")
                }
            }
            .flatMap { pageNumber ->
                if (pageNumber == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    service.getCharactersPage(pageNumber)
                        .map { page -> insertToDbTransactionally(loadType, page) }
                        .map<MediatorResult> { page -> MediatorResult.Success(endOfPaginationReached = page.info.next == null) }
                        .onErrorReturn { error -> MediatorResult.Error(error) }
                }
            }
            .onErrorReturn { MediatorResult.Error(it) }
    }

    private fun insertToDbTransactionally(
        loadType: LoadType,
        data: ApiCharactersPage,
    ): ApiCharactersPage {
        database.runInTransaction {
            if (loadType == LoadType.REFRESH) database.characterDao.clearAll()
            val prevKey = data.info.prev.apiId()
            val nextKey = data.info.next.apiId()
            val keys = data.results.map {
                RoomCharacter.RemoteKey(characterId = it.id, prevKey = prevKey, nextKey = nextKey)
            }
            database.characterDao.insertAll(data.results.map { it.toRoomCharacter() })
            database.characterRemoteKeyDao.insertAll(keys)
        }
        return data
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, RoomCharacter>): RoomCharacter.RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                database.characterRemoteKeyDao.remoteKeyByCharacterId(character.id)
            }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, RoomCharacter>): RoomCharacter.RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                database.characterRemoteKeyDao.remoteKeyByCharacterId(character.id)
            }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RoomCharacter>): RoomCharacter.RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.characterRemoteKeyDao.remoteKeyByCharacterId(id)
            }
        }
    }

    companion object {
        const val INVALID_PAGE = -1
    }

}