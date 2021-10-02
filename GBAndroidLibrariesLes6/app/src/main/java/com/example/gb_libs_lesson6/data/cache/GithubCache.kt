package com.example.gb_libs_lesson6.data.cache

import com.example.gb_libs_lesson6.data.cache.room.GithubDatabase
import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser

interface GithubCache {

    // User

    fun getAllUsers(): List<GithubUser>

    fun getUserByLogin(login: String): GithubUser?

    fun insertUser(users: List<GithubUser>)

    // Repository

    fun getRepoByUser(user: GithubUser): List<GithubRepo>

    fun insertRepo(repos: List<GithubRepo>, owner: GithubUser)

    companion object Factory {
        fun build(): GithubCacheImpl {
            return GithubCacheImpl(GithubDatabase.getInstance())
        }
    }
}