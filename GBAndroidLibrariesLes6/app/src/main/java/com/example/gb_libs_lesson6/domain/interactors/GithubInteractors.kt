package com.example.gb_libs_lesson6.domain.interactors

import com.example.gb_libs_lesson6.data.cache.GithubCache
import com.example.gb_libs_lesson6.data.remote.GithubService
import com.example.gb_libs_lesson6.utils.INetworkStatus

data class GithubInteractors(
    val getUsers: GetUsers,
    val getUserRepos: GetUserRepos
) {
    companion object Factory {
        fun build(
            githubService: GithubService,
            networkStatus: INetworkStatus,
            githubCache: GithubCache
        ): GithubInteractors {
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