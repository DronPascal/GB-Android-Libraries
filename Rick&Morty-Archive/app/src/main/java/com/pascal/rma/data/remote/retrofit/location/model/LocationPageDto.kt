package com.pascal.rma.data.remote.retrofit.location.model

data class LocationPageDto(
    val info: InfoDto,
    val results: List<LocationDto>
)