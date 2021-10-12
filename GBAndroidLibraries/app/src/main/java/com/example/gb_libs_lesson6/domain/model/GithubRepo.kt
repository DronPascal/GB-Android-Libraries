package com.example.gb_libs_lesson6.domain.model

import android.os.Parcelable
import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubRepo
import com.example.gb_libs_lesson6.data.remote.model.GithubRepoDto
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepo(
    val id: String,
    val name: String,
    val forksCount: Int,
) : Parcelable


fun GithubRepo.toGithubRepoDto(): GithubRepoDto {
    return GithubRepoDto(
        id = id,
        name = name,
        forksCount = forksCount
    )
}

fun GithubRepo.toRoomGithubRepos(userId: String): RoomGithubRepo {
    return RoomGithubRepo(
        id = id,
        name = name,
        forksCount = forksCount,
        userId = userId
    )
}

fun List<GithubRepo>.toRoomGithubRepos(userId: String): List<RoomGithubRepo> {
    return map { it.toRoomGithubRepos(userId) }
}