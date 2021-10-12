package com.example.gb_libs_lesson6.di.module

import android.content.Context
import com.example.gb_libs_lesson6.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun app(): Context {
        return app
    }
}