package com.example.gb_libs_lesson6.presentation.screens.users.adapter

import com.example.gb_libs_lesson6.presentation.items.IItemView

interface UserItemView : IItemView {

    fun showLogin(login: String)

    fun loadAvatar(url: String)
}