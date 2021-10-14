package com.pascal.rma.di.module

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.pascal.rma.data.local.cache.CharacterCache
import com.pascal.rma.data.local.cache.room.character.CharacterDatabase
import com.pascal.rma.data.paging.character.CharactersRemoteMediator
import com.pascal.rma.data.remote.CharacterService
import com.pascal.rma.data.remote.retrofit.character.CharacterApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 10.10.2021.
 */
@Module
object DataCharacterModule {

    private const val CHARACTER_DB_NAME = "characters.db"

    @Singleton
    @Provides
    fun provideCharacterDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context, CharacterDatabase::class.java, CHARACTER_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterRemoteMediator(
        characterApiService: CharacterApiService,
        characterDatabase: CharacterDatabase
    ): CharactersRemoteMediator {
        return CharactersRemoteMediator(
            characterApiService,
            characterDatabase
        )
    }

    @ExperimentalPagingApi
    @Singleton
    @Provides
    fun provideCharacterCache(
        charactersRemoteMediator: CharactersRemoteMediator,
        characterDatabase: CharacterDatabase
    ): CharacterCache {
        return CharacterCache(
            characterDatabase,
            charactersRemoteMediator
        )
    }

    @Singleton
    @Provides
    fun provideCharacterService(
        characterApiService: CharacterApiService
    ): CharacterService {
        return CharacterService(characterApiService)
    }

}