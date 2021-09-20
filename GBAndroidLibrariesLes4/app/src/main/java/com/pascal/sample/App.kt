package com.pascal.sample

import android.app.Application
import com.pascal.sample.data.InternalStorageImpl

/**
 * Created by dronpascal on 20.09.2021.
 */
class App : Application() {

    val internalStorageImpl by lazy {
        InternalStorageImpl(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        lateinit var instance: App
    }
}