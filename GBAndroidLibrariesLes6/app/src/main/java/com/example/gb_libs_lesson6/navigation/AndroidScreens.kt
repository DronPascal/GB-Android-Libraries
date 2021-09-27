package com.example.gb_libs_lesson6.navigation

import com.example.gb_libs_lesson6.ui.screens.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }
}