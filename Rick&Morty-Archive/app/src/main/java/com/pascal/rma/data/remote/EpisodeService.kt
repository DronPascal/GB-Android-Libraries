package com.pascal.rma.data.remote

import com.pascal.rma.data.remote.retrofit.episode.ApiEpisodeMapper.toEpisode
import com.pascal.rma.data.remote.retrofit.episode.EpisodeApiService
import com.pascal.rma.domain.model.Episode
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 08.10.2021.
 */
class EpisodeService(
    private val apiService: EpisodeApiService
) : IEpisodeService {

    override fun getEpisodes(ids: List<Int>): Single<List<Episode>> {
        val idsQuery = ids.joinToString(separator = ",")
        return when (ids.size) {
            1 -> apiService.getEpisodeById(ids.first())
                .flatMap {
                    Single.fromCallable { listOf(it.toEpisode()) }
                }
            else -> apiService.getEpisodesByIds(idsQuery)
                .map { episodes ->
                    episodes.map { it.toEpisode() }
                }
        }
    }

}