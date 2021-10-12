package com.example.gb_libs_lesson6.data.remote

import com.example.gb_libs_lesson6.data.remote.model.GithubRepoDto
import com.example.gb_libs_lesson6.data.remote.model.GithubUserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserDto>>

    @GET
    fun getUserRepos(@Url reposUrl: String): Single<List<GithubRepoDto>>

}