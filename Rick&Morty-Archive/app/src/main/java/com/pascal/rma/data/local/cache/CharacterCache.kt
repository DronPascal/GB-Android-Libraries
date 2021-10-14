package com.pascal.rma.data.local.cache

import androidx.paging.*
import androidx.paging.rxjava3.flowable
import com.pascal.rma.data.local.cache.room.character.CharacterDatabase
import com.pascal.rma.data.local.cache.room.character.RoomCharacterMappers.toCharacter
import com.pascal.rma.data.paging.character.CharactersRemoteMediator
import com.pascal.rma.domain.model.Character
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharacterCache @ExperimentalPagingApi constructor(
    private val database: CharacterDatabase,
    private val characterRemoteMediator: CharactersRemoteMediator,
) : ICharacterCache {

    @ExperimentalCoroutinesApi
    @OptIn(ExperimentalPagingApi::class)
    override fun getAll(): Flowable<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 60,
                prefetchDistance = 20,
                initialLoadSize = 40
            ),
            remoteMediator = characterRemoteMediator,
            pagingSourceFactory = { database.characterDao.selectAll() }
        )
            .flowable
            .map { pagingData ->
                pagingData.map { it.toCharacter() }
            }
    }

    override fun getCharacter(characterId: Int): Single<Character> {
        return database.characterDao
            .select(characterId)
            .map { it.toCharacter() }
    }


}