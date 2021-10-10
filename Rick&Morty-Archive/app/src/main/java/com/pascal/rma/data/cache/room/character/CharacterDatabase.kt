package com.pascal.rma.data.cache.room.character

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascal.rma.data.cache.room.character.dao.CharacterDao
import com.pascal.rma.data.cache.room.character.dao.CharacterEpisodeDao
import com.pascal.rma.data.cache.room.character.dao.CharacterRemoteKeyDao

/**
 * Created by dronpascal on 05.10.2021.
 */
@Database(
    entities = [
        RoomCharacter::class,
        RoomCharacter.RemoteKey::class,
        RoomCharacter.Episode::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val characterEpisodeDao: CharacterEpisodeDao
    abstract val characterRemoteKeyDao: CharacterRemoteKeyDao
}