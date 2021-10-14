package com.pascal.rma.presentation.screens.character.detail

import com.pascal.rma.domain.interactors.GetEpisodes
import com.pascal.rma.domain.model.Character
import com.pascal.rma.domain.model.Episode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharacterDetailPresenter : MvpPresenter<CharacterDetailView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var getEpisodes: GetEpisodes

    var character: Character? = null
    var episodes: List<Episode> = emptyList()
    private val disposable = CompositeDisposable()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
        viewState.initView()
    }

    private fun loadData() {
        viewState.initData()

        character?.episodeIds?.let { episodeIds ->
            disposable.add(
                getEpisodes.execute(episodeIds)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { episodeList ->
                        episodes = episodeList
                        if (episodes.isNotEmpty()) viewState.initEpisodeView()
                    }
            )
        }
    }

    fun onBackPressed(): Boolean {
        disposable.dispose()
        router.exit()
        return true
    }

}