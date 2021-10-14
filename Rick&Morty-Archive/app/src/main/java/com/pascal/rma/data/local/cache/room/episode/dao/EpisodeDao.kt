package com.pascal.rma.data.local.cache.room.episode.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rma.data.local.cache.room.character.RoomCharacter
import com.pascal.rma.data.local.cache.room.episode.RoomEpisode
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 05.10.2021.
 */
@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episodes ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, RoomEpisode>

    @Query("SELECT * FROM episodes WHERE id = :episodeId")
    fun select(episodeId: Int): RoomEpisode

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(episodes: List<RoomEpisode>)

    @Query("DELETE FROM episodes")
    fun clearAll()

}