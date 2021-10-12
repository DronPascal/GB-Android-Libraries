package com.example.gb_libs_lesson6.data.cache.room

import androidx.room.*
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubRepo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubRepo>)

    @Update
    fun update(user: RoomGithubRepo)

    @Update
    fun update(vararg users: RoomGithubRepo)

    @Update
    fun update(users: List<RoomGithubRepo>)

    @Delete
    fun delete(user: RoomGithubRepo)

    @Delete
    fun delete(vararg users: RoomGithubRepo)

    @Delete
    fun delete(users: List<RoomGithubRepo>)

    @Query("SELECT * FROM RoomGithubRepo")
    fun getAll(): List<RoomGithubRepo>

    @Query("SELECT * FROM RoomGithubRepo WHERE userId = :userId")
    fun getByUserId(userId: String): List<RoomGithubRepo>
}