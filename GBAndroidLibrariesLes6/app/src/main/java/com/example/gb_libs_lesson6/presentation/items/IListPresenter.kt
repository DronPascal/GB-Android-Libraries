package com.example.gb_libs_lesson6.presentation.items

import com.example.gb_libs_lesson6.presentation.screens.users.adapter.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>