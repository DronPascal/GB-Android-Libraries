package com.pascal.rma.domain.model

/**
 * Created by dronpascal on 05.10.2021.
 */
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: List<String>,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
