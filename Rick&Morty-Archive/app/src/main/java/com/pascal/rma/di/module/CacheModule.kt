package com.pascal.rma.di.module

import android.content.Context
import androidx.room.Room
import com.pascal.rma.data.cache.room.character.CharacterDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 10.10.2021.
 */
@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideCharacterDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context, CharacterDatabase::class.java, CHARACTER_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private const val CHARACTER_DB_NAME = "characters.db"
    }
}