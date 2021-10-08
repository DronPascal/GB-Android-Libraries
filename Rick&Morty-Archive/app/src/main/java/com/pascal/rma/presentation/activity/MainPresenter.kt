package com.pascal.rma.presentation.activity

import com.pascal.rma.presentation.navigation.AppScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class MainPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AppScreens.CategoriesScreen())
    }

    fun backPressed() {
        router.exit()
    }
}