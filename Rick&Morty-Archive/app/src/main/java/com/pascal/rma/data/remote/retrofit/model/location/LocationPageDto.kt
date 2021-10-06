package com.pascal.rma.data.remote.retrofit.model.location

data class LocationPageDto(
    val info: InfoDto,
    val results: List<LocationDto>
)