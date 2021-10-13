package com.pascal.rma.presentation.screens.categories

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

/**
 * Created by dronpascal on 05.10.2021.
 */
interface CategoriesView : MvpView {

    @AddToEndSingle
    fun initView()

    @OneExecution
    fun showSnackbar(message: String)
}