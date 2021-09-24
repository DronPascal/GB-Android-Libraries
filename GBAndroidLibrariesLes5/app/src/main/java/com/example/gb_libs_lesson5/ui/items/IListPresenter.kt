package com.example.gb_libs_lesson5.ui.items

import com.example.gb_libs_lesson5.ui.screens.repos.adapter.UserRepoItemView
import com.example.gb_libs_lesson5.ui.screens.users.adapter.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>

interface IUserRepoListPresenter : IListPresenter<UserRepoItemView>