package com.pascal.rma.data.remote

import com.pascal.rma.domain.model.Character
import com.pascal.rma.domain.model.Episode
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 05.10.2021.
 */
interface ICharacterService {
    fun getCharacter(id: Int) : Single<Character>
}

interface ILocationService {}

interface IEpisodeService {
    fun getEpisodes(ids: List<Int>): Single<List<Episode>>
}