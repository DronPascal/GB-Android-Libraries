package com.pascal.rma.domain.model

/**
 * Created by dronpascal on 05.10.2021.
 */
data class Episode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
