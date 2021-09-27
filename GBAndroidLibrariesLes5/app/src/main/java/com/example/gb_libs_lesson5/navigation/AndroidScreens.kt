package com.example.gb_libs_lesson5.navigation

import com.example.gb_libs_lesson5.data.model.GithubUserRepoItem
import com.example.gb_libs_lesson5.ui.screens.repo.RepoFragment
import com.example.gb_libs_lesson5.ui.screens.repos.UserReposFragment
import com.example.gb_libs_lesson5.ui.screens.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserReposScreen(private val url: String) : SupportAppScreen() {
        override fun getFragment() = UserReposFragment.newInstance(url)
    }

    class RepoScreen(private val repo: GithubUserRepoItem) : SupportAppScreen() {
        override fun getFragment() = RepoFragment.newInstance(repo)
    }
}