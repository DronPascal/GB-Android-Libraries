package com.pascal.rma.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pascal.rma.data.remote.retrofit.ApiHolder
import com.pascal.rma.data.remote.retrofit.IApiHolder
import com.pascal.rma.data.remote.retrofit.character.CharacterApiService
import com.pascal.rma.data.remote.retrofit.episode.EpisodeApiService
import com.pascal.rma.data.remote.retrofit.location.LocationApiService
import com.pascal.rma.data.local.network.AndroidNetworkStatus
import com.pascal.rma.data.local.network.INetworkStatus
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        fun baseUrl(): String = "https://rickandmortyapi.com/api/"

        @Provides
        @Singleton
        fun gson(): Gson = GsonBuilder()
            .create()

        @Provides
        @Singleton
        fun logger(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        }

        @Provides
        @Singleton
        fun client(logger: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        }

        @Singleton
        @Provides
        fun CharacterApiService(
            @Named("baseUrl") baseUrl: String,
            client: OkHttpClient,
            gson: Gson
        ): CharacterApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(CharacterApiService::class.java)
        }

        @Singleton
        @Provides
        fun LocationApiService(
            @Named("baseUrl") baseUrl: String,
            gson: Gson
        ): LocationApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(LocationApiService::class.java)
        }

        @Singleton
        @Provides
        fun EpisodeApiService(
            @Named("baseUrl") baseUrl: String,
            gson: Gson
        ): EpisodeApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(EpisodeApiService::class.java)
        }

        @Provides
        @Singleton
        fun networkStatus(context: Context): INetworkStatus =
            AndroidNetworkStatus(context)
    }
}

