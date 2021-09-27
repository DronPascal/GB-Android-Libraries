package com.example.gb_libs_lesson5.ui.screens.repo

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoView: MvpView {
    fun init()
}