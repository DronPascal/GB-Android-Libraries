package com.example.gb_libs_lesson5.ui.screens.repos.adapter

import com.example.gb_libs_lesson5.ui.items.IItemView

interface UserRepoItemView : IItemView {

    fun showRepoName(name: String)
}