package com.example.gb_libs_lesson6.di.module

import android.content.Context
import com.example.gb_libs_lesson6.data.remote.ApiHolder
import com.example.gb_libs_lesson6.data.remote.GithubService
import com.example.gb_libs_lesson6.data.remote.IApiHolder
import com.example.gb_libs_lesson6.utils.AndroidNetworkStatus
import com.example.gb_libs_lesson6.utils.INetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
interface ApiModule {

    @Binds
    fun apiHolder(impl: ApiHolder): IApiHolder

    companion object {

        @Provides
        @Singleton
        @Named("baseUrl")
        fun baseUrl(): String = "https://api.github.com"

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
        ): GithubService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GithubService::class.java)
        }

        @Provides
        @Singleton
        fun networkStatus(context: Context): INetworkStatus =
            AndroidNetworkStatus(context)
    }
}
