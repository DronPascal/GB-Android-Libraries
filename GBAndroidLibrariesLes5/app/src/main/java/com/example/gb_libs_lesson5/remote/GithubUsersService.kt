package com.example.gb_libs_lesson5.remote

import com.example.gb_libs_lesson5.data.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubUsersService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}