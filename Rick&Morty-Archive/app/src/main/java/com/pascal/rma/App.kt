package com.pascal.rma

import android.app.Application
import com.pascal.rma.di.component.AppComponent
import com.pascal.rma.di.component.DaggerAppComponent
import com.pascal.rma.di.module.AppModule

/**
 * Created by dronpascal on 05.10.2021.
 */
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