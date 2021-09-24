package com.pascal.sample.domain

/**
 * Created by dronpascal on 20.09.2021.
 */
interface Storage {

    fun cacheFileFromAssets(fileName: String)

    fun convertFileToPngAndSave(fileName: String)

}