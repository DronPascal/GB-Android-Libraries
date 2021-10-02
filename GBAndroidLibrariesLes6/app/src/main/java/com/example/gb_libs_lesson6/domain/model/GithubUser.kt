package com.example.gb_libs_lesson6.domain.model

import android.os.Parcelable
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubUser
import com.example.gb_libs_lesson6.data.remote.model.GithubUserDto
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val id: String,
    val avatarUrl: String,
    val reposUrl: String,
) : Parcelable

fun GithubUser.toGithubUserDto(): GithubUserDto {
    return GithubUserDto(
        login = login,
        id = id.toLong(),
        avatarUrl = avatarUrl,
        reposUrl = reposUrl
    )
}

fun GithubUser.toRoomGithubUser(): RoomGithubUser {
    return RoomGithubUser(
        login = login,
        id = id,
        avatarUrl = avatarUrl,
        reposUrl = reposUrl
    )
}

fun List<GithubUser>.toRoomGithubUsers(): List<RoomGithubUser> {
    return map { it.toRoomGithubUser() }
}
