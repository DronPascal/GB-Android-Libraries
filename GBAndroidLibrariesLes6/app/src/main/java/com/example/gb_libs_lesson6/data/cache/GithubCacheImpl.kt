package com.example.gb_libs_lesson6.data.cache

import com.example.gb_libs_lesson6.data.cache.room.GithubDatabase
import com.example.gb_libs_lesson6.data.cache.room.model.toGithubRepos
import com.example.gb_libs_lesson6.data.cache.room.model.toGithubUser
import com.example.gb_libs_lesson6.data.cache.room.model.toGithubUsers
import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.domain.model.toRoomGithubRepos
import com.example.gb_libs_lesson6.domain.model.toRoomGithubUsers

class GithubCacheImpl(private val db: GithubDatabase) : GithubCache {

    override fun getAllUsers(): List<GithubUser> {
        return db.userDao.getAll().toGithubUsers()
    }

    override fun getUserByLogin(login: String): GithubUser? {
        return db.userDao.getByLogin(login)?.toGithubUser()
    }

    override fun insertUser(users: List<GithubUser>) {
        db.userDao.insert(users.toRoomGithubUsers())
    }

    override fun getRepoByUser(user: GithubUser): List<GithubRepo> {
        val roomUser = db.userDao.getByLogin(user.login)
            ?: throw Exception("Repo not cached.")
        return db.repoDao.getByUserId(roomUser.id).toGithubRepos()
    }

    override fun insertRepo(repos: List<GithubRepo>, owner: GithubUser) {
        val roomUser = db.userDao.getByLogin(owner.login)
            ?: throw Exception("User not cached.")
        db.repoDao.insert(repos.toRoomGithubRepos(roomUser.login))
    }

}