package com.example.gb_libs_lesson5.remote

import com.example.gb_libs_lesson5.data.model.GithubUserItem
import com.example.gb_libs_lesson5.data.model.GithubUserRepoItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url




interface GithubService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserItem>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubUserRepoItem>>
}