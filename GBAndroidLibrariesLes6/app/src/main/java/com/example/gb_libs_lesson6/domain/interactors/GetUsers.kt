package com.example.gb_libs_lesson6.domain.interactors

import com.example.gb_libs_lesson6.data.cache.GithubCache
import com.example.gb_libs_lesson6.data.remote.GithubService
import com.example.gb_libs_lesson6.data.remote.model.toGithubUser
import com.example.gb_libs_lesson6.data.remote.model.toGithubUsers
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GetUsers(
    private val cache: GithubCache,
    private val service: GithubService,
    private val networkStatus: INetworkStatus,
) {
    fun execute(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                service.getUsers().flatMap { apiUsers ->
                    Single.fromCallable {
                        val users = apiUsers.toGithubUsers()
                        cache.insertUser(users)
                        users
                    }
                }
            } else {
                Single.fromCallable {
                    cache.getAllUsers()
                }
            }
        }
}