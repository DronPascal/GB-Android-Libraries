package com.example.gb_libs_lesson1.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {

    private val users = Observable.fromIterable(listOf(
        GithubUser("user1"),
        GithubUser("user2"),
        GithubUser("user3"),
        GithubUser("user4"),
        GithubUser("user5")
    ))

    fun getUsersObservable() = users
}