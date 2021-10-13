package com.pascal.rma.presentation.screens.character.detail

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

/**
 * Created by dronpascal on 05.10.2021.
 */
interface CharacterDetailView : MvpView {

    @AddToEndSingle
    fun initView()

    @OneExecution
    fun initData()

}