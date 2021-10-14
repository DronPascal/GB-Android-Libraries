package com.pascal.rma.data.local.cache

import com.pascal.rma.data.local.cache.room.episode.EpisodeDatabase
import com.pascal.rma.data.local.cache.room.episode.RoomEpisodeMappers.toEpisode
import com.pascal.rma.data.local.cache.room.episode.RoomEpisodeMappers.toRoomEpisode
import com.pascal.rma.domain.model.Episode
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 08.10.2021.
 */
class EpisodeCache(
    private val database: EpisodeDatabase
) : IEpisodeCache {

    override fun getEpisodes(episodeIds: List<Int>): Single<List<Episode>> {
        return Single.fromCallable {
            val resultList = mutableListOf<Episode>()
            episodeIds.forEach { id ->
                resultList.add(
                    database.episodeDao.select(id).toEpisode().also { println(it) }
                )
            }
            println("RESULT LIST $resultList")
            resultList.toList()
        }
    }

    override fun insertAll(episodes: List<Episode>) {
        database.episodeDao.insertAll(episodes.map { it.toRoomEpisode() })
    }

}