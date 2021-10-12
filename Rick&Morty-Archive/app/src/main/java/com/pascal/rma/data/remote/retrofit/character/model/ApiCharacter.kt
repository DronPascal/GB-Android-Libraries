package com.pascal.rma.data.remote.retrofit.character.model

data class ApiCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: ApiLocation,
    val name: String,
    val origin: ApiOrigin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)