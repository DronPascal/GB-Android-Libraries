package com.pascal.rma.util

import android.content.Context
import android.content.res.Configuration

/**
 * Created by dronpascal on 13.10.2021.
 */
object ConfigurationUtil {

    fun Context.isPortrait(): Boolean = resources.configuration
        .orientation == Configuration.ORIENTATION_PORTRAIT

}