package com.pascal.rma.data.local.cache.room.character.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rma.data.local.cache.room.character.RoomCharacter
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 05.10.2021.
 */
@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, RoomCharacter>

    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun select(characterId: Int): Single<RoomCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<RoomCharacter>)

    @Query("DELETE FROM characters")
    fun clearAll()

}