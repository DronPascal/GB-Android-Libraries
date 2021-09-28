package com.example.gb_libs_lesson6.data.cache.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gb_libs_lesson6.data.remote.model.GithubUserDto
import com.example.gb_libs_lesson6.domain.model.GithubUser

@Entity
data class RoomGithubUser(
    @PrimaryKey val id: String,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
)

fun RoomGithubUser.toGithubUser(): GithubUser {
    return GithubUser(
        login = login,
        id = id,
        avatarUrl = avatarUrl,
        reposUrl = reposUrl
    )
}