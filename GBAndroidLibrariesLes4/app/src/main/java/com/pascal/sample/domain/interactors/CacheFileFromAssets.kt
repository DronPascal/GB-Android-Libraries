package com.pascal.sample.domain.interactors

import com.pascal.sample.domain.Storage
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Completable
import java.io.IOException

/**
 * Created by dronpascal on 20.09.2021.
 */
class CacheFileFromAssets(
    private val storage: Storage
) {
    fun execute(fileName: String): @NonNull Completable =
        Completable.create { emitter ->
            try {
                storage.cacheFileFromAssets(fileName)
                emitter.onComplete()
            } catch (e: IOException) {
                emitter.onError(e)
            }
        }
}