package com.example.gb_libs_lesson6.data.cache.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.gb_libs_lesson6.domain.model.GithubRepo

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepo(
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: Int,
    val userId: String,
)

fun RoomGithubRepo.toGithubRepo(): GithubRepo {
    return GithubRepo(
        id = id,
        name = name,
        forksCount = forksCount
    )
}

fun List<RoomGithubRepo>.toGithubRepos(): List<GithubRepo> {
    return map { it.toGithubRepo() }
}