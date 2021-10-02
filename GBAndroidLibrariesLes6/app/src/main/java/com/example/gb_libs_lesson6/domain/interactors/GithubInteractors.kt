package com.example.gb_libs_lesson6.domain.interactors

import com.example.gb_libs_lesson6.data.cache.GithubCache
import com.example.gb_libs_lesson6.data.remote.ApiHolder
import com.example.gb_libs_lesson6.utils.INetworkStatus

data class GithubInteractors(
    val getUsers: GetUsers,
    val getUserRepos: GetUserRepos
) {
    companion object Factory {
        fun build(networkStatus: INetworkStatus): GithubInteractors {
            val githubCache = GithubCache.build()
            val githubService = ApiHolder.githubApiService

            return GithubInteractors(
                getUsers = GetUsers(
                    cache = githubCache,
                    service = githubService,
                    networkStatus = networkStatus,
                ),
                getUserRepos = GetUserRepos(
                    cache = githubCache,
                    service = githubService,
                    networkStatus = networkStatus,
                )
            )
        }
    }
}