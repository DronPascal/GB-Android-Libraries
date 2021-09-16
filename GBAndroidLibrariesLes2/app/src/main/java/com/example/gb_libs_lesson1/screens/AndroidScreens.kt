package com.example.gb_libs_lesson1.screens

import com.example.gb_libs_lesson1.model.GithubUser
import com.example.gb_libs_lesson1.view.ui.user_detail.UserDetailFragment
import com.example.gb_libs_lesson1.view.ui.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }


    class UserDetailScreen(private val githubUser: GithubUser) : SupportAppScreen() {

        override fun getFragment() = UserDetailFragment.newInstance(githubUser)
    }
}