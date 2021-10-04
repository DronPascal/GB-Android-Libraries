package com.example.gb_libs_lesson6.presentation.screens.repos

import android.util.Log
import com.example.gb_libs_lesson6.domain.interactors.GetUserRepos
import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.presentation.items.IUserRepoListPresenter
import com.example.gb_libs_lesson6.presentation.navigation.AndroidScreens
import com.example.gb_libs_lesson6.presentation.screens.repos.adapter.UserRepoItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserReposPresenter : MvpPresenter<UserReposView>() {

    @Inject
    lateinit var getUserRepos: GetUserRepos

    @Inject
    lateinit var router: Router

    class UserRepoListPresenter : IUserRepoListPresenter {

        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((UserRepoItemView) -> Unit)? = null

        override fun getCount(): Int = repos.size

        override fun bindView(view: UserRepoItemView) {
            val repo = repos[view.pos]
            view.showRepoName(repo.name)
        }
    }

    val userRepoListPresenter = UserRepoListPresenter()
    var user: GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        userRepoListPresenter.itemClickListener = { itemView ->
            val githubRepo = userRepoListPresenter.repos[itemView.pos]
            navigateToRepoDetail(githubRepo = githubRepo)
        }
    }

    private fun navigateToRepoDetail(githubRepo: GithubRepo) {
        router.navigateTo(AndroidScreens.RepoScreen(githubRepo))
    }

    private fun loadData() {
        user?.let {
            getUserRepos.execute(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repos ->
                    userRepoListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    Log.e("UsersPresenter", "Ошибка получения репозиториев", it)
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}