package com.pascal.rma.data.local.cache.room.episode

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascal.rma.data.local.cache.room.episode.dao.EpisodeDao
import com.pascal.rma.data.local.cache.room.episode.dao.EpisodeRemoteKeyDao

/**
 * Created by dronpascal on 09.10.2021.
 */
@Database(
    entities = [
        RoomEpisode::class,
        RoomEpisode.RemoteKey::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class EpisodeDatabase : RoomDatabase() {
    abstract val episodeDao: EpisodeDao
    abstract val episodeRemoteKeyDao: EpisodeRemoteKeyDao
}