package com.example.gb_libs_lesson1.presentation

import com.example.gb_libs_lesson1.model.GithubUser
import com.example.gb_libs_lesson1.model.GithubUsersRepo
import com.example.gb_libs_lesson1.screens.AndroidScreens
import com.example.gb_libs_lesson1.view.UserItemView
import com.example.gb_libs_lesson1.view.ui.users.UsersView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val githubUser = usersListPresenter.users[itemView.pos]
            navigateToUserDetail(argument = githubUser)
        }
    }

    private fun navigateToUserDetail(argument: GithubUser) {
        router.navigateTo(AndroidScreens.UserDetailScreen(argument))
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}