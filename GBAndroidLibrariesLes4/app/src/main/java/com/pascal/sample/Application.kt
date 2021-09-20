package com.pascal.sample

import android.app.Application
import com.pascal.sample.data.InternalStorageImpl

/**
 * Created by dronpascal on 20.09.2021.
 */
class Application : Application() {

    val internalStorageImpl = InternalStorageImpl(applicationContext)

}