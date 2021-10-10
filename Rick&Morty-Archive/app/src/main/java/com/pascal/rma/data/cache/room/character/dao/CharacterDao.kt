package com.pascal.rma.data.cache.room.character.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.pascal.rma.data.cache.room.character.RoomCharacter

/**
 * Created by dronpascal on 05.10.2021.
 */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: RoomCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characters: List<RoomCharacter>)

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAll(): PagingSource<Int, RoomCharacter>

    @Query("DELETE FROM characters")
    fun clearCharacters()

}