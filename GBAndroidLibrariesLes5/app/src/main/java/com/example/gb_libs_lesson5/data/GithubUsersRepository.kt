package com.example.gb_libs_lesson5.data

import com.example.gb_libs_lesson5.remote.ApiHolder

class GithubUsersRepository {
    fun getUsers() = ApiHolder.apiService.getUsers()
}