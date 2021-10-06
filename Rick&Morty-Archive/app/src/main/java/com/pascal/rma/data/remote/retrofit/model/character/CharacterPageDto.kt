package com.pascal.rma.data.remote.retrofit.model.character

data class CharacterPageDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)