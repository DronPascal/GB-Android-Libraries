package com.pascal.sample.domain.interactors

import com.pascal.sample.domain.Storage

/**
 * Created by dronpascal on 20.09.2021.
 */
class StorageInteractors(
    val cacheFileFromAssets: CacheFileFromAssets,
    val convertFileToPngAndSave: ConvertFileToPngAndSave,
) {
    companion object Factory {
        fun build(storage: Storage): StorageInteractors {
            return StorageInteractors(
                cacheFileFromAssets = CacheFileFromAssets(storage),
                convertFileToPngAndSave = ConvertFileToPngAndSave(storage)
            )
        }
    }
}