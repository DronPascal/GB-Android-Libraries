package com.pascal.rma.data.remote.retrofit.character.model

data class ApiPageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)