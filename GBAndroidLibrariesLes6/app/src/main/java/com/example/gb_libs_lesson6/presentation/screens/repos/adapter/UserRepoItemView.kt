package com.example.gb_libs_lesson6.presentation.screens.repos.adapter

import com.example.gb_libs_lesson6.presentation.items.IItemView

interface UserRepoItemView : IItemView {

    fun showRepoName(name: String)
}