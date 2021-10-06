package com.pascal.rma.data.remote.retrofit

import com.pascal.rma.data.remote.retrofit.model.character.CharacterPageDto
import com.pascal.rma.data.remote.retrofit.model.episode.EpisodePageDto
import com.pascal.rma.data.remote.retrofit.model.location.LocationPageDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by dronpascal on 05.10.2021.
 */
interface RmApiService {

    @GET("/character")
    fun getCharacters(): Single<CharacterPageDto>

    @GET("/location")
    fun getLocations(): Single<LocationPageDto>

    @GET("/episode")
    fun getEpisodes(): Single<EpisodePageDto>

    fun getCharacterPage(@Url pageUrl: String): Single<CharacterPageDto>

    fun getLocationPage(@Url pageUrl: String): Single<LocationPageDto>

    fun getEpisodePage(@Url pageUrl: String): Single<EpisodePageDto>
}