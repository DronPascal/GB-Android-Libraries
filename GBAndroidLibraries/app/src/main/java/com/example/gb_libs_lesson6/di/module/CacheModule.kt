package com.example.gb_libs_lesson6.di.module

import android.content.Context
import androidx.room.Room
import com.example.gb_libs_lesson6.data.cache.GithubCache
import com.example.gb_libs_lesson6.data.cache.room.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(context: Context): GithubDatabase {
        return Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubCache(db: GithubDatabase): GithubCache {
        return GithubCache.build(db)
    }

    companion object {
        private const val DB_NAME = "database.db"
    }
}