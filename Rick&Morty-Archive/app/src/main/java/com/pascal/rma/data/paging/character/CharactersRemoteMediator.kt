package com.pascal.rma.data.paging.character

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.pascal.rma.data.local.cache.room.character.CharacterDatabase
import com.pascal.rma.data.local.cache.room.character.RoomCharacter
import com.pascal.rma.data.local.cache.room.character.RoomCharacterMappers.toRoomCharacter
import com.pascal.rma.data.remote.retrofit.character.CharacterApiService
import com.pascal.rma.data.remote.retrofit.character.model.ApiCharactersPage
import com.pascal.rma.util.StringUtil.pageId
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

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
                        // TODO On REFRESH remoteKey is always null
                        //  as state.anchorPosition always null...
                        //  This issue may be paging 3 fault
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        val curKey = remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
                        curKey
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                        // ?: throw InvalidObjectException("Result is empty")
                        val prevKey = remoteKeys?.prevKey ?: INVALID_PAGE
                        prevKey
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                        // ?: throw InvalidObjectException("Result is empty")
                        // TODO fix FIRST APP LAUNCH ONLY bug (2nd PagingState.pages.size = 0)
                        //  I think its room related bug of async runInTransaction() or db initializing.
                        //  Maybe DB.prepend with some default characters.db will help.
                        //  Best solution - migrate to coroutines and use suspend withTransaction()
                        //  !! STARTING_PAGE_INDEX + 1 is tricky way to avoid this issue
                        val nextKey = remoteKeys?.nextKey ?: STARTING_PAGE_INDEX + 1 //INVALID_PAGE
                        nextKey
                    }
                }
            }
            .flatMap { pageNumber ->
                if (pageNumber == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    service.getCharactersPage(pageNumber)
                        .subscribeOn(Schedulers.io())
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
        if (loadType == LoadType.REFRESH) database.characterDao.clearAll()
        database.runInTransaction {
            val prevKey = data.info.prev.pageId()
            val nextKey = data.info.next.pageId()
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
        const val INVALID_PAGE = 0
        const val STARTING_PAGE_INDEX = 1
    }

}