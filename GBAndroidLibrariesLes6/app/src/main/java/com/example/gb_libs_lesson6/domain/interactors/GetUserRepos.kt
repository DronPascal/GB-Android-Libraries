package com.example.gb_libs_lesson6.domain.interactors

import com.example.gb_libs_lesson6.data.cache.GithubCache
import com.example.gb_libs_lesson6.data.remote.GithubService
import com.example.gb_libs_lesson6.data.remote.model.toGithubRepo
import com.example.gb_libs_lesson6.data.remote.model.toGithubRepos
import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GetUserRepos(
    private val cache: GithubCache,
    private val service: GithubService,
    private val networkStatus: INetworkStatus,
) {
    fun execute(user: GithubUser): Single<List<GithubRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                service.getUserRepos(user.reposUrl).flatMap { apiRepos ->
                    Single.fromCallable {
                        val repos = apiRepos.toGithubRepos()
                        cache.insertRepo(repos = repos, owner = user)
                        repos
                    }
                }
            } else {
                Single.fromCallable {
                    cache.getRepoByUser(user)
                }
            }
        }
}