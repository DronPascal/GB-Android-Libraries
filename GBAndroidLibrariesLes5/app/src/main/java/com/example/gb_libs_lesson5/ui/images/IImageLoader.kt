package com.example.gb_libs_lesson5.ui.images

interface IImageLoader<T> {

    fun loadTo(url: String, target: T)
}