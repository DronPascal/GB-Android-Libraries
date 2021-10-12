package com.pascal.rma.presentation.screens.character.list

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Created by dronpascal on 05.10.2021.
 */
interface CharactersView : MvpView {

    @AddToEndSingle
    fun initView()

    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = "show_hide_retry")
    fun showRetryAlertDialog(message: String?)

    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = "show_hide_retry")
    fun hideRetryAlertDialog()

}