package com.example.gb_libs_lesson6.presentation.screens.users

import android.util.Log
import com.example.gb_libs_lesson6.domain.interactors.GithubInteractors
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.presentation.items.IUserListPresenter
import com.example.gb_libs_lesson6.presentation.screens.users.adapter.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val githubInteractors: GithubInteractors,
    private val router: Router,
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

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
            // todo
        }
    }

    private fun loadData() {
        githubInteractors.getUsers.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                loadRepos(it.first())
                it
            }
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения пользователей", it)
            })
    }

    private fun loadRepos(user: GithubUser) {
        githubInteractors.getUserRepos.execute(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                Log.d("UsersPresenter", "${repos.first()}")
            }, {
                Log.e("UsersPresenter", "Ошибка получения пользователей", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}