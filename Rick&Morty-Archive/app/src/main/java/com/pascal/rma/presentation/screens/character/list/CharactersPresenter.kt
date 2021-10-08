package com.pascal.rma.presentation.screens.character.list

import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharactersPresenter : MvpPresenter<CharactersView>() {

    @Inject
    lateinit var router: Router


    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}