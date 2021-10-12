package com.pascal.rma.presentation.screens.character.list

import androidx.paging.PagingData
import com.pascal.rma.data.cache.CharacterCache
import com.pascal.rma.domain.model.Character
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharactersPresenter : MvpPresenter<CharactersView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var cache: CharacterCache

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    @ExperimentalCoroutinesApi
    fun getCharacters(): Flowable<PagingData<Character>> {
        return cache.getAll()
    }

    fun onRetryDialogConfirm() {
        viewState.hideRetryAlertDialog()    }

    fun onRetryDialogCancel() {
        viewState.hideRetryAlertDialog()
    }

    fun onPagingError(error: Throwable) {
       viewState.showRetryAlertDialog(error.localizedMessage)
    }
}