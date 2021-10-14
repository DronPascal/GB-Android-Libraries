package com.pascal.rma.data.local.cache.room.character.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rma.data.local.cache.room.character.RoomCharacter

/**
 * Created by dronpascal on 10.10.2021.
 */
@Dao
interface CharacterRemoteKeyDao {

    @Query("SELECT * FROM character_remote_keys WHERE characterId = :characterId")
    fun remoteKeyByCharacterId(characterId: Int): RoomCharacter.RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(keys: List<RoomCharacter.RemoteKey>)

    @Query("DELETE FROM character_remote_keys")
    fun clearAll()

}