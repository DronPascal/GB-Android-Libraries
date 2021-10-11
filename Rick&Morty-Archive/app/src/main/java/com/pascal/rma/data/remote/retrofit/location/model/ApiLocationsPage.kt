package com.pascal.rma.data.remote.retrofit.location.model

data class ApiLocationsPage(
    val info: ApiPageInfo,
    val results: List<ApiLocation>
)