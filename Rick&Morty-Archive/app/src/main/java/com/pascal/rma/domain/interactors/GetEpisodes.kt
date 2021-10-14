package com.pascal.rma.domain.interactors

import com.pascal.rma.data.local.cache.IEpisodeCache
import com.pascal.rma.data.local.network.INetworkStatus
import com.pascal.rma.data.remote.IEpisodeService
import com.pascal.rma.domain.model.Episode
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 14.10.2021.
 */
class GetEpisodes(
    private val cache: IEpisodeCache,
    private val service: IEpisodeService,
    private val networkStatus: INetworkStatus
) {

    fun execute(episodeIds: List<Int>): Single<List<Episode>> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                service.getEpisodes(episodeIds)
                    .flatMap {
                        cache.insertAll(it)
                        // cache.getEpisodes(episodeIds)
                        Single.fromCallable { it }
                    }
            } else {
                cache.getEpisodes(episodeIds)
            }
        }.onErrorReturn { emptyList() }
    }

}