package com.example.gb_libs_lesson6.data.remote

import javax.inject.Inject

interface IApiHolder {

    val apiService: GithubService
}

class ApiHolder @Inject constructor(
    override val apiService: GithubService
) : IApiHolder