package com.pascal.rma.presentation.screens.categories

import com.pascal.rma.presentation.navigation.AppScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CategoriesPresenter : MvpPresenter<CategoriesView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun onNavigateToCharacters() {
        router.navigateTo(AppScreens.CharactersScreen())
    }

    fun onNavigateToLocations() {
        TODO("Not yet implemented")
    }

    fun onNavigateToEpisodes() {
        TODO("Not yet implemented")
    }

}