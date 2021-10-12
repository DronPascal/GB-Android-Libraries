package com.pascal.rma.presentation.screens.character.list

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.pascal.rma.domain.interactors.GetCharacters
import com.pascal.rma.domain.model.Character
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.MvpPresenter
import moxy.presenterScope
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharactersPresenter : MvpPresenter<CharactersView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var getCharacters: GetCharacters

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    @ExperimentalCoroutinesApi
    fun getCharactersFlowable(): Flowable<PagingData<Character>> {
        return getCharacters.execute().cachedIn(presenterScope)
    }

    fun onRetryDialogConfirm() {
        viewState.hideRetryAlertDialog()
    }

    fun onRetryDialogCancel() {
        viewState.hideRetryAlertDialog()
    }

    fun onPagingError(error: Throwable) {
        viewState.showRetryAlertDialog(error.localizedMessage)
    }
}