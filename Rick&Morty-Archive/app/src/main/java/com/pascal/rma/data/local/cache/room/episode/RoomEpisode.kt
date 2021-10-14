package com.pascal.rma.data.local.cache.room.episode

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by dronpascal on 05.10.2021.
 */
@Entity(tableName = "episodes")
data class RoomEpisode(
    @PrimaryKey
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
    val characterIds: String?
) {

    @Entity(
        tableName = "episode_remote_keys",
        foreignKeys = [ForeignKey(
            entity = RoomEpisode::class,
            parentColumns = ["id"],
            childColumns = ["episodeId"],
            onDelete = ForeignKey.CASCADE
        )]
    )
    data class RemoteKey(
        @PrimaryKey val episodeId: Int,
        val prevKey: Int?,
        val nextKey: Int?
    )

}