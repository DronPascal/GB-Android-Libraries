package com.pascal.rma.util

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 05.10.2021.
 */
interface INetworkStatus {
    fun isOnline(): Observable<Boolean>

    fun isOnlineSingle(): Single<Boolean>
}