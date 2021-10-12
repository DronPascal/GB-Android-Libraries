package com.pascal.rma.data.remote.retrofit.character.model

data class ApiCharactersPage(
    val info: ApiPageInfo,
    val results: List<ApiCharacter>
)