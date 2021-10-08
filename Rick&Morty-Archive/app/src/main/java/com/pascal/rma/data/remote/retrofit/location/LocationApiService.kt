package com.pascal.rma.data.remote.retrofit.location

import com.pascal.rma.data.remote.retrofit.location.model.LocationPageDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface LocationApiService {

    @GET("/location")
    fun getLocations(): Single<LocationPageDto>

    fun getLocationPage(@Url pageUrl: String): Single<LocationPageDto>
}