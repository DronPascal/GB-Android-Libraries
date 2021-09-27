package com.example.gb_libs_lesson6.remote

import com.example.gb_libs_lesson6.data.GithubRepository
import com.example.gb_libs_lesson6.data.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubUsersService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url reposUrl: String): Single<List<GithubRepository>>
}