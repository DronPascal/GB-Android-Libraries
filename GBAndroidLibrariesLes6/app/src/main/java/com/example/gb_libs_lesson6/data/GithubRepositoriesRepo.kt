package com.example.gb_libs_lesson6.data

import com.example.gb_libs_lesson6.data.db.GithubDatabase
import com.example.gb_libs_lesson6.data.db.RoomGithubRepository
import com.example.gb_libs_lesson6.remote.ApiHolder
import com.example.gb_libs_lesson6.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubRepositoriesRepo(
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) {

    fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.apiService.getRepositories(user.reposUrl.orEmpty()).flatMap { repositories ->
                Single.fromCallable {
                    val roomUser = db.userDao.getByLogin(user.login.orEmpty()) ?: error("")
                    val roomRepos = repositories.map {
                        RoomGithubRepository(
                            id = it.id,
                            name = it.name,
                            forksCount = it.forksCount,
                            userId = roomUser.id
                        )
                    }
                    db.repositoryDao.insert(roomRepos)
                    repositories
                }
            }
        } else {
            Single.fromCallable {
                val roomUser = db.userDao.getByLogin(user.login.orEmpty()) ?: error("")
                db.repositoryDao.getByUserId(roomUser.id).map {
                    GithubRepository(it.id, it.name, it.forksCount)
                }
            }
        }
    }
}