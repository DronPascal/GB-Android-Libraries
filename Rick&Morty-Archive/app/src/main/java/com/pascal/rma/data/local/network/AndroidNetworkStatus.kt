package com.pascal.rma.data.local.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * Created by dronpascal on 05.10.2021.
 */
class AndroidNetworkStatus(context: Context) : INetworkStatus {

    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusSubject.onNext(false)

        val connectivityManager = context.getSystemService<ConnectivityManager>()
        val request = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onUnavailable() {
                    statusSubject.onNext(false)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnline(): Observable<Boolean> {
        return statusSubject
    }

    override fun isOnlineSingle(): Single<Boolean> {
        return statusSubject.first(false)
    }

}