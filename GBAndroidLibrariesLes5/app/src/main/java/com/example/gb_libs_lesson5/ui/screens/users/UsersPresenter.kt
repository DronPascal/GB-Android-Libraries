package com.example.gb_libs_lesson5.ui.screens.users

import android.util.Log
import com.example.gb_libs_lesson5.data.GithubUsersRepository
import com.example.gb_libs_lesson5.data.model.GithubUserItem
import com.example.gb_libs_lesson5.navigation.AndroidScreens
import com.example.gb_libs_lesson5.ui.items.IUserListPresenter
import com.example.gb_libs_lesson5.ui.screens.users.adapter.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val usersRepository: GithubUsersRepository,
    private val router: Router,
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUserItem>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login.orEmpty())
            view.loadAvatar(user.avatarUrl.orEmpty())
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val githubUser = usersListPresenter.users[itemView.pos]
            githubUser.reposUrl?.let { navigateToUserRepos(url = it) }
        }
    }

    private fun navigateToUserRepos(url: String) {
        router.navigateTo(AndroidScreens.UserReposScreen(url))
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения пользователей", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}