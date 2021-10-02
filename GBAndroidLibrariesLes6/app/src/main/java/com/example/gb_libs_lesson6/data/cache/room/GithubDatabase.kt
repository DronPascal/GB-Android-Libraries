package com.example.gb_libs_lesson6.data.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubRepo
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubUser

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepo::class],
    version = 1
)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {

        private const val DB_NAME = "database.db"

        private var instance: GithubDatabase? = null

        fun getInstance() = instance ?: throw IllegalStateException("База данных не инициализирована")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}