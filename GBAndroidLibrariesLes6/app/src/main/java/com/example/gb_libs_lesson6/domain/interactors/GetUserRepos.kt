package com.example.gb_libs_lesson6.domain.interactors

import com.example.gb_libs_lesson6.data.cache.room.GithubDatabase
import com.example.gb_libs_lesson6.data.cache.room.model.toGithubRepo
import com.example.gb_libs_lesson6.data.remote.GithubService
import com.example.gb_libs_lesson6.data.remote.model.toGithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.domain.model.toRoomGithubRepo
import com.example.gb_libs_lesson6.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GetUserRepos(
    private val cache: GithubDatabase,
    private val service: GithubService,
    private val networkStatus: INetworkStatus,
) {
    fun execute(user: GithubUser): Single<List<GithubRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                service.getUserRepos(user.reposUrl).flatMap { apiRepos ->
                    Single.fromCallable {
                        val roomUser =
                            cache.userDao.getByLogin(user.login)
                                ?: throw Exception("User not cached.")
                        val repos = apiRepos.map { apiRepo ->
                            apiRepo.toGithubRepo()
                        }
                        val roomRepos = repos.map { repo ->
                            repo.toRoomGithubRepo(roomUser.id)
                        }
                        cache.repositoryDao.insert(roomRepos)
                        repos
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = cache.userDao.getByLogin(user.login)
                        ?: throw Exception("Repo not cached.")
                    cache.repositoryDao.getByUserId(roomUser.id).map { roomRepo ->
                        roomRepo.toGithubRepo()
                    }
                }
            }
        }
}