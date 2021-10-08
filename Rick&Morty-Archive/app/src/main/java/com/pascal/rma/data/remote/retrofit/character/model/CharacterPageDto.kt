package com.pascal.rma.data.remote.retrofit.character.model

data class CharacterPageDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)