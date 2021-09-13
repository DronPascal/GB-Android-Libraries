package com.example.gb_libs_lesson1.presentation

import com.example.gb_libs_lesson1.model.GithubUser
import com.example.gb_libs_lesson1.view.ui.user_detail.UserDetailView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserDetailPresenter(
    val router: Router
) : MvpPresenter<UserDetailView>() {

    var user: GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}