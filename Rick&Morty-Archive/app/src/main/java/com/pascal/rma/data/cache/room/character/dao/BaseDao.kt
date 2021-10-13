package com.pascal.rma.data.cache.room.character.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by dronpascal on 13.10.2021.
 */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<T>)

}