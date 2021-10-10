package com.pascal.rma.data.cache.room.character.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rma.data.cache.room.character.RoomCharacter
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 10.10.2021.
 */
@Dao
interface CharacterEpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(episodes: List<RoomCharacter.Episode>)

    @Query("SELECT * FROM character_episodes ORDER BY episodeId ASC")
    fun qetAll(): Single<List<RoomCharacter.Episode>>

    @Query("DELETE FROM character_episodes")
    fun clearRemoteKeys()

}