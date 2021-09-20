package com.pascal.sample.presentation

import com.pascal.sample.domain.FileName
import com.pascal.sample.domain.interactors.StorageInteractors
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.showToast("File converted successfully!")
                }, { error ->
                    viewState.showToast(error.localizedMessage)
                }
            )
    }
}