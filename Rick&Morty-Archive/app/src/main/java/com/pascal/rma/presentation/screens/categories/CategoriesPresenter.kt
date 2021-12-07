package com.pascal.rma.presentation.screens.categories

import com.pascal.rma.presentation.navigation.AppScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CategoriesPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<CategoriesView>() {

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
        viewState.showSnackbar("The section is not yet available")
    }

    fun onNavigateToEpisodes() {
        viewState.showSnackbar("The section is not yet available")
    }

}