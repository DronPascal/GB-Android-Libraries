package com.example.gb_libs_lesson6.data.remote.model

import android.os.Parcelable
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubUser
import com.example.gb_libs_lesson6.data.cache.room.model.toGithubUser
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserDto(
    @Expose
    val login: String? = null,

    @Expose
    val id: Long? = null,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String? = null,
) : Parcelable

fun GithubUserDto.toGithubUser(): GithubUser {
    return GithubUser(
        login = login.orEmpty(),
        id = id.toString(),
        avatarUrl = avatarUrl.orEmpty(),
        reposUrl = reposUrl.orEmpty()
    )
}

fun List<GithubUserDto>.toGithubUsers(): List<GithubUser> {
    return map { it.toGithubUser() }
}