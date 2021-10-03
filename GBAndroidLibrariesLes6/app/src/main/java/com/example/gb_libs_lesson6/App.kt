package com.example.gb_libs_lesson6

import android.app.Application
import com.example.gb_libs_lesson6.di.component.AppComponent
import com.example.gb_libs_lesson6.di.component.DaggerAppComponent
import com.example.gb_libs_lesson6.di.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {

        lateinit var instance: App
    }
}