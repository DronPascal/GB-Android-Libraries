package com.example.gb_libs_lesson6.presentation.screens.repo

import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(
    private val router: Router,
) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}