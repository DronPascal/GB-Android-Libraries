package com.pascal.rma.presentation.screens.character.detail

import com.pascal.rma.domain.model.Character
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharacterDetailPresenter : MvpPresenter<CharacterDetailView>() {

    @Inject
    lateinit var router: Router

    var character: Character? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initData()
        viewState.initView()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}