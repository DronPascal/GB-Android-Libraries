package com.pascal.rma.data.cache.room.character

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by dronpascal on 05.10.2021.
 */
@Entity(tableName = "characters")
data class RoomCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: String,
    val originId: Int?,
    val location: String,
    val locationId: Int?,
) {

    @Entity(
        tableName = "character_remote_keys",
        foreignKeys = [ForeignKey(
            entity = RoomCharacter::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        )]
    )
    data class RemoteKey(
        @PrimaryKey val characterId: Int,
        val prevKey: Int?,
        val nextKey: Int?
    )

    @Entity(
        tableName = "character_episodes",
        foreignKeys = [ForeignKey(
            entity = RoomCharacter::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        )]
    )
    data class Episode(
        @PrimaryKey val characterId: String,
        val episodeId: Int,
        val name: String,
    )

}