package com.pascal.sample.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

/**
 * Created by dronpascal on 20.09.2021.
 */
interface MainView : MvpView {

    @OneExecution
    fun showToast(msg: String?)

}