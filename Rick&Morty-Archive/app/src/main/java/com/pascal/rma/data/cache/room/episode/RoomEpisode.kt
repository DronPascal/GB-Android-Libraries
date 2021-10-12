package com.pascal.rma.data.cache.room.episode

/**
 * Created by dronpascal on 05.10.2021.
 */
data class RoomEpisode(
    val air_date: String,
    val characterIds: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)