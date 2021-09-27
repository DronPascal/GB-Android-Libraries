package com.example.gb_libs_lesson6.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: Int,
    val userId: String,
)
