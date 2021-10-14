package com.pascal.rma.data.local.cache.room.episode.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rma.data.local.cache.room.episode.RoomEpisode

/**
 * Created by dronpascal on 14.10.2021.
 */
@Dao
interface EpisodeRemoteKeyDao {

    @Query("SELECT * FROM episode_remote_keys WHERE episodeId = :episodeId")
    fun remoteKeyByEpisodeId(episodeId: Int): RoomEpisode.RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(episodes: List<RoomEpisode>)

    @Query("DELETE FROM episode_remote_keys")
    fun clearAll()

}