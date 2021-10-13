package com.pascal.rma.data.cache.room.character.dao

import androidx.room.Dao
import androidx.room.Query
import com.pascal.rma.data.cache.room.character.RoomCharacter

/**
 * Created by dronpascal on 10.10.2021.
 */
@Dao
interface CharacterRemoteKeyDao : BaseDao<RoomCharacter.RemoteKey> {

    @Query("SELECT * FROM character_remote_keys WHERE characterId = :characterId")
    fun remoteKeyByCharacterId(characterId: Int): RoomCharacter.RemoteKey?

    @Query("DELETE FROM character_remote_keys")
    fun clearAll()

}