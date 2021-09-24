package com.example.gb_libs_lesson5.data

import com.example.gb_libs_lesson5.remote.ApiHolder

class GithubUserRepoRepository {

        fun getUserRepos(url: String) = ApiHolder.apiService.getUserRepos(url)
}