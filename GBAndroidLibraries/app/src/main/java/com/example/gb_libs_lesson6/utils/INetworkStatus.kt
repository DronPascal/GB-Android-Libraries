package com.example.gb_libs_lesson6.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun isOnline(): Observable<Boolean>

    fun isOnlineSingle(): Single<Boolean>
}