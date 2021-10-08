package com.pascal.rma.di.module

import android.content.Context
import com.pascal.rma.App
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