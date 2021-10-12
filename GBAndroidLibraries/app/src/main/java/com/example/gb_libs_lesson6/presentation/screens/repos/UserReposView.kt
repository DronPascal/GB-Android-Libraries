package com.example.gb_libs_lesson6.presentation.screens.repos

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserReposView : MvpView {
    fun init()
    fun updateList()
}