package com.pascal.sample.presentation

import com.pascal.sample.domain.FileName
import com.pascal.sample.domain.interactors.StorageInteractors
import moxy.MvpPresenter

/**
 * Created by dronpascal on 20.09.2021.
 */
class MainPresenter(private val storageInteractors: StorageInteractors) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        cacheFileFromAssets()
    }

    fun cacheFileFromAssets() {
        storageInteractors
            .cacheFileFromAssets
            .execute(FileName.flowerFile)
            .subscribe(
                {
                    viewState.showToast("File initialization done successfully!")
                }, { error ->
                    viewState.showToast(error.localizedMessage)
                }
            )

    }

    fun onConvertAndSaveFile(fileName: String) {
        storageInteractors
            .convertFileToPngAndSave
            .execute(fileName)
            .subscribe(
                {
                    viewState.showToast("File converted successfully!")
                }, { error ->
                    viewState.showToast(error.localizedMessage)
                }
            )
    }
}