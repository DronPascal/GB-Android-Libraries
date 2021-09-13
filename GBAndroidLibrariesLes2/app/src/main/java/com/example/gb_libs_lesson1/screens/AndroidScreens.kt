package com.example.gb_libs_lesson1.screens

import com.example.gb_libs_lesson1.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }
}