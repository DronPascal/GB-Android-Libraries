package com.example.gb_libs_lesson6.ui.screens.users.adapter

import com.example.gb_libs_lesson6.ui.items.IItemView

interface UserItemView : IItemView {

    fun showLogin(login: String)

    fun loadAvatar(url: String)
}