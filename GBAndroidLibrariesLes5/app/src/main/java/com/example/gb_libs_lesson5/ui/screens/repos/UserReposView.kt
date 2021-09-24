package com.example.gb_libs_lesson5.ui.screens.repos

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserReposView: MvpView {
    fun init()
    fun updateList()
}