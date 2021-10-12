package com.pascal.rma.domain.interactors

import androidx.paging.PagingData
import com.pascal.rma.data.cache.ICharacterCache
import com.pascal.rma.domain.model.Character
import io.reactivex.rxjava3.core.Flowable

/**
 * Created by dronpascal on 10.10.2021.
 */
class GetCharacters(
    private val characterCache: ICharacterCache
) {
    fun execute(): Flowable<PagingData<Character>> =
        characterCache.getAll()
    // TODO Here we can add filtering
    // .map { pagingData -> pagingData.filter { it.name == "smth" } }
}