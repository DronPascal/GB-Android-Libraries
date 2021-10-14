package com.pascal.rma.data.remote.retrofit.episode

import com.pascal.rma.data.remote.retrofit.episode.model.ApiEpisode
import com.pascal.rma.data.remote.retrofit.episode.model.ApiEpisodesPage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("episode/")
    fun getEpisodePage(
        @Query("page") page: Int,
    ): Single<ApiEpisodesPage>

    @GET("episode/{id}")
    fun getEpisodeById(
        @Path("id") id: Int
    ): Single<ApiEpisode>

    @GET("episode/{ids}")
    fun getEpisodesByIds(
        @Path("ids") ids: String
    ): Single<List<ApiEpisode>>

}