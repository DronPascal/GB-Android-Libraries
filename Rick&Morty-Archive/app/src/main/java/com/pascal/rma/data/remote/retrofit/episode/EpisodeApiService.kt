package com.pascal.rma.data.remote.retrofit.episode

import com.pascal.rma.data.remote.retrofit.episode.model.EpisodePageDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface EpisodeApiService {

    @GET("/episode")
    fun getEpisodes(): Single<EpisodePageDto>

    fun getEpisodePage(@Url pageUrl: String): Single<EpisodePageDto>
}