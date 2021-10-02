package com.example.gb_libs_lesson6.data.remote.model

import com.example.gb_libs_lesson6.data.cache.room.model.RoomGithubUser
import com.example.gb_libs_lesson6.data.cache.room.model.toGithubUser
import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.google.gson.annotations.Expose

data class GithubRepoDto(
    @Expose
    val id: String,
    @Expose
    val name: String,
    @Expose
    val forksCount: Int,
)

fun GithubRepoDto.toGithubRepo(): GithubRepo {
    return GithubRepo(
        id = id,
        name = name,
        forksCount = forksCount
    )
}

fun List<GithubRepoDto>.toGithubRepos(): List<GithubRepo> {
    return map { it.toGithubRepo() }
}