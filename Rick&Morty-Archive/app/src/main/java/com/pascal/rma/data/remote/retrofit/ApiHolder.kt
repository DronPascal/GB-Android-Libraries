package com.pascal.rma.data.remote.retrofit

import com.pascal.rma.data.remote.retrofit.character.CharacterApiService
import com.pascal.rma.data.remote.retrofit.episode.EpisodeApiService
import com.pascal.rma.data.remote.retrofit.location.LocationApiService
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class ApiHolder @Inject constructor(
    override val characterApiService: CharacterApiService,
    override val locationApiService: LocationApiService,
    override val episodeApiService: EpisodeApiService
) : IApiHolder

interface IApiHolder {
    val characterApiService: CharacterApiService
    val locationApiService: LocationApiService
    val episodeApiService: EpisodeApiService
}
