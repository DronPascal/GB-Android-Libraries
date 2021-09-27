package com.example.gb_libs_lesson6

import android.app.Application
import com.example.gb_libs_lesson6.data.db.GithubDatabase
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigationHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        GithubDatabase.create(this)
    }

    companion object {

        lateinit var instance: App
    }
}