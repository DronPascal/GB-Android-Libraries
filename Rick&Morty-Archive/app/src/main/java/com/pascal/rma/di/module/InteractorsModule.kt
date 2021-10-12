package com.pascal.rma.di.module

import com.pascal.rma.data.cache.CharacterCache
import com.pascal.rma.domain.interactors.GetCharacters
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dronpascal on 12.10.2021.
 */
@Module
object InteractorsModule {

    @Provides
    @Singleton
    fun provideGetCharacters(characterCache: CharacterCache): GetCharacters {
        return GetCharacters(characterCache)
    }
    
}