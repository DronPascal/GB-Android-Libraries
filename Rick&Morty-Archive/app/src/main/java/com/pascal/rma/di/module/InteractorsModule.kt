package com.pascal.rma.di.module

import com.pascal.rma.data.local.cache.CharacterCache
import com.pascal.rma.data.local.cache.EpisodeCache
import com.pascal.rma.data.local.network.AndroidNetworkStatus
import com.pascal.rma.data.local.network.INetworkStatus
import com.pascal.rma.data.remote.EpisodeService
import com.pascal.rma.domain.interactors.GetCharacterFlow
import com.pascal.rma.domain.interactors.GetEpisodes
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
    fun provideGetCharacterFlow(characterCache: CharacterCache): GetCharacterFlow {
        return GetCharacterFlow(characterCache)
    }

    @Provides
    @Singleton
    fun provideGetEpisodes(
        episodeCache: EpisodeCache,
        episodeService: EpisodeService,
        networkStatus: INetworkStatus
    ): GetEpisodes {
        return GetEpisodes(
            episodeCache,
            episodeService,
            networkStatus
        )
    }

}