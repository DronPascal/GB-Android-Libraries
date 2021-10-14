package com.pascal.rma.presentation.screens.character.list

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.pascal.rma.domain.interactors.GetCharacterFlow
import com.pascal.rma.domain.model.Character
import com.pascal.rma.presentation.navigation.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
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
    lateinit var getCharacterFlow: GetCharacterFlow

    var lastScrollPosition = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
    }

    @ExperimentalCoroutinesApi
    fun getCharactersFlowable(): Flowable<PagingData<Character>> {
        return getCharacterFlow.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .cachedIn(presenterScope)
    }

    fun onNavigateToCharacterDetail(character: Character) {
        router.navigateTo(AppScreens.CharacterDetailScreen(character))
    }

    fun onPagingError(error: Throwable) {
        viewState.showRetryAlertDialog(error.localizedMessage)
    }

    fun onRetryDialogConfirm() {
        viewState.hideRetryAlertDialog()
    }

    fun onRetryDialogCancel() {
        viewState.hideRetryAlertDialog()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}
