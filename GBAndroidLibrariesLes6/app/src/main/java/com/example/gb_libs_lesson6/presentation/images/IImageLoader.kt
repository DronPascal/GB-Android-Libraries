package com.example.gb_libs_lesson6.presentation.images

interface IImageLoader<T> {

    fun loadTo(url: String, target: T)
}