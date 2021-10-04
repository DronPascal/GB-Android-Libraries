package com.example.gb_libs_lesson6.presentation.screens.repo

import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoPresenter : MvpPresenter<RepoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}