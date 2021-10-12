package com.example.gb_libs_lesson6.presentation.navigation

import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.example.gb_libs_lesson6.domain.model.GithubUser
import com.example.gb_libs_lesson6.presentation.screens.repo.RepoFragment
import com.example.gb_libs_lesson6.presentation.screens.repos.UserReposFragment
import com.example.gb_libs_lesson6.presentation.screens.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserReposScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserReposFragment.newInstance(user)
    }

    class RepoScreen(private val repo: GithubRepo) : SupportAppScreen() {
        override fun getFragment() = RepoFragment.newInstance(repo)
    }
}