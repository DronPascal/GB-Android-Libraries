package com.pascal.rma.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pascal.rma.data.remote.retrofit.ApiHolder
import com.pascal.rma.data.remote.retrofit.IApiHolder
import com.pascal.rma.data.remote.retrofit.RmApiService
import com.pascal.rma.util.AndroidNetworkStatus
import com.pascal.rma.util.INetworkStatus
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by dronpascal on 05.10.2021.
 */
@Module
interface ApiModule {

    @Binds
    fun apiHolder(impl: ApiHolder): IApiHolder

    companion object {

        @Provides
        @Singleton
        @Named("baseUrl")
        fun baseUrl(): String = "https://rickandmortyapi.com/api"

        @Provides
        @Singleton
        fun gson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        @Singleton
        @Provides
        fun githubService(
            @Named("baseUrl") baseUrl: String,
            gson: Gson
        ): RmApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RmApiService::class.java)
        }

        @Provides
        @Singleton
        fun networkStatus(context: Context): INetworkStatus =
            AndroidNetworkStatus(context)
    }
}
