package com.example.gb_libs_lesson1.presentation

import com.example.gb_libs_lesson1.view.IItemView
import com.example.gb_libs_lesson1.view.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>