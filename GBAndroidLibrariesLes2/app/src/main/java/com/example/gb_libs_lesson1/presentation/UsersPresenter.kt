package com.example.gb_libs_lesson1.presentation

import android.util.Log
import com.example.gb_libs_lesson1.model.GithubUser
import com.example.gb_libs_lesson1.model.GithubUsersRepo
import com.example.gb_libs_lesson1.screens.AndroidScreens
import com.example.gb_libs_lesson1.view.UserItemView
import com.example.gb_libs_lesson1.view.ui.users.UsersView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
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
        observeGithubUsers()

        usersListPresenter.itemClickListener = { itemView ->
            val githubUser = usersListPresenter.users[itemView.pos]
            navigateToUserDetail(argument = githubUser)
        }
    }

    private fun navigateToUserDetail(argument: GithubUser) {
        router.navigateTo(AndroidScreens.UserDetailScreen(argument))
    }

    private fun observeGithubUsers() {
        val usersObservable = usersRepo.getUsersObservable()
        usersObservable.subscribe(usersObserver)
    }

    fun backPressed(): Boolean {
        usersObserver.disposable?.dispose()
        router.exit()
        return true
    }

    private val usersObserver = object : Observer<GithubUser> {
        var disposable: Disposable? = null

        private val TAG = "UsersObserver"
        override fun onNext(user: GithubUser) {
            usersListPresenter.users.add(user)
            viewState.updateList()
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "Error")
        }

        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "Subscribe")
            disposable = d
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }
    }
}