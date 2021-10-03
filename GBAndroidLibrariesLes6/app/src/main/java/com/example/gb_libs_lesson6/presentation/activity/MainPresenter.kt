package com.example.gb_libs_lesson6.presentation.activity

import com.example.gb_libs_lesson6.presentation.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}