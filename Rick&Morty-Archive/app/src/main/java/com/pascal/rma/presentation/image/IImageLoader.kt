package com.pascal.rma.presentation.image

/**
 * Created by dronpascal on 05.10.2021.
 */
interface IImageLoader<T> {

    fun loadTo(url: String, target: T)
}