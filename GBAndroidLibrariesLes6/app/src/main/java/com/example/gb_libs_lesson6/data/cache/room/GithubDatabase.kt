package com.example.gb_libs_lesson6.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubRepo
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubUser

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepo::class],
    version = 1
)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repoDao: RepoDao
}