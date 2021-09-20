package com.pascal.sample.presentation

import com.pascal.sample.domain.FileName
import com.pascal.sample.domain.interactors.StorageInteractors
import moxy.MvpPresenter

/**
 * Created by dronpascal on 20.09.2021.
 */
class MainPresenter : MvpPresenter<MainView>() {

    lateinit var storageInteractors: StorageInteractors

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        cacheFileFromAssets()
    }

    private fun cacheFileFromAssets() {
        storageInteractors
            .cacheFileFromAssets
            .execute(FileName.flowerFile)
            .subscribe(
                {
                    viewState.showToast("File converted successfully!")
                }, { error ->
                    viewState.showError(error.localizedMessage)
                }
            )

    }

    private fun onConvertAndSaveFile(fileName: String) {
        storageInteractors
    }
}