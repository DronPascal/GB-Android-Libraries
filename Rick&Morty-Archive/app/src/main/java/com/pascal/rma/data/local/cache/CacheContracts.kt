package com.pascal.rma.data.local.cache

import androidx.paging.PagingData
import com.pascal.rma.domain.model.Character
import com.pascal.rma.domain.model.Episode
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 05.10.2021.
 */
interface ICharacterCache {
    fun getAll(): Flowable<PagingData<Character>>
    fun getCharacter(characterId: Int): Single<Character>
}

interface ILocationCache {}

interface IEpisodeCache {
    fun getEpisodes(episodeIds: List<Int>): Single<List<Episode>>
    fun insertAll(episodes: List<Episode>)
}