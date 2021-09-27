package com.example.gb_libs_lesson6.data

import com.example.gb_libs_lesson6.data.db.GithubDatabase
import com.example.gb_libs_lesson6.data.db.RoomGithubUser
import com.example.gb_libs_lesson6.remote.ApiHolder
import com.example.gb_libs_lesson6.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersRepo(
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) {

    fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.apiService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(
                                user.id.toString(),
                                user.login.orEmpty(),
                                user.avatarUrl.orEmpty(),
                                user.reposUrl.orEmpty()
                            )
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(
                        login = roomUser.login,
                        id = roomUser.id.toLong(),
                        avatarUrl = roomUser.avatarUrl,
                        reposUrl = roomUser.reposUrl,
                    )
                }
            }
        }
    }
}