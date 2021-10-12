package com.pascal.rma.data.cache

import androidx.paging.PagingData
import com.pascal.rma.domain.model.Character
import io.reactivex.rxjava3.core.Flowable

/**
 * Created by dronpascal on 05.10.2021.
 */
interface ICharacterCache {
    fun getAll(): Flowable<PagingData<Character>>
}

interface ILocationCache {}

interface IEpisodeCache {}