package com.example.gb_libs_lesson5.ui.screens.repos

import android.util.Log
import com.example.gb_libs_lesson5.data.GithubUserRepoRepository
import com.example.gb_libs_lesson5.data.model.GithubUserRepoItem
import com.example.gb_libs_lesson5.navigation.AndroidScreens
import com.example.gb_libs_lesson5.ui.items.IUserRepoListPresenter
import com.example.gb_libs_lesson5.ui.screens.repos.adapter.UserRepoItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserReposPresenter(
    private val userRepoRepository: GithubUserRepoRepository,
    private val router: Router,
) : MvpPresenter<UserReposView>() {

    class UserRepoListPresenter : IUserRepoListPresenter {

        val repos = mutableListOf<GithubUserRepoItem>()

        override var itemClickListener: ((UserRepoItemView) -> Unit)? = null

        override fun getCount(): Int = repos.size

        override fun bindView(view: UserRepoItemView) {
            val repo = repos[view.pos]
            println(repo)
            view.showRepoName(repo.fullName.orEmpty())
        }
    }

    val userRepoListPresenter = UserRepoListPresenter()
    var reposUrl: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        userRepoListPresenter.itemClickListener = { itemView ->
            val githubRepo = userRepoListPresenter.repos[itemView.pos]
            navigateToRepoDetail(githubRepo = githubRepo)
        }
    }

    private fun navigateToRepoDetail(githubRepo: GithubUserRepoItem) {
        router.navigateTo(AndroidScreens.RepoScreen(githubRepo))
    }

    private fun loadData() {
        userRepoRepository.getUserRepos(url = reposUrl!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                userRepoListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения репозиториев", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}