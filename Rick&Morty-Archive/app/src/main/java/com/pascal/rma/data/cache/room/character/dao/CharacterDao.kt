package com.pascal.rma.data.cache.room.character.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rma.data.cache.room.character.RoomCharacter

/**
 * Created by dronpascal on 05.10.2021.
 */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: RoomCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<RoomCharacter>)

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, RoomCharacter>

    @Query("DELETE FROM characters")
    fun clearAll()

}