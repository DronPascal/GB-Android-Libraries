package com.pascal.rma.di.module

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.pascal.rma.data.local.cache.EpisodeCache
import com.pascal.rma.data.local.cache.room.episode.EpisodeDatabase
import com.pascal.rma.data.remote.EpisodeService
import com.pascal.rma.data.remote.retrofit.episode.EpisodeApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 14.10.2021.
 */
@Module
object DataEpisodeModule {

    private const val EPISODE_DB_NAME = "episodes.db"

    @Singleton
    @Provides
    fun provideEpisodeDatabase(context: Context): EpisodeDatabase {
        return Room.databaseBuilder(
            context, EpisodeDatabase::class.java, EPISODE_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // TODO Implement episode mediator
//    @Singleton
//    @Provides
//    fun provideEpisodeRemoteMediator(
//        episodeApiService: EpisodeApiService,
//        episodeDatabase: EpisodeDatabase
//    ): EpisodesRemoteMediator {
//        return EpisodesRemoteMediator(
//            episodeApiService,
//            episodeDatabase
//        )
//    }

    @ExperimentalPagingApi
    @Singleton
    @Provides
    fun provideEpisodeCache(
        episodeDatabase: EpisodeDatabase
    ): EpisodeCache {
        return EpisodeCache(episodeDatabase)
    }

    @Singleton
    @Provides
    fun provideEpisodeService(
        episodeApiService: EpisodeApiService
    ): EpisodeService {
        return EpisodeService(episodeApiService)
    }

}