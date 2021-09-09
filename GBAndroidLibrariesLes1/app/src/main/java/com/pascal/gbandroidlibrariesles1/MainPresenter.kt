package com.pascal.gbandroidlibrariesles1

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel
) {

    fun counterClick(id: Int) {
        val nextValue = model.next(id)
        view.setButtonText(id, nextValue.toString())
    }

}