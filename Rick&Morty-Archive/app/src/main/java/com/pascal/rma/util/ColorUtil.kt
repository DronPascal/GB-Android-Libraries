package com.pascal.rma.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

/**
 * Created by dronpascal on 13.10.2021.
 */
object ColorUtil {

    @ColorInt
    fun Context.getThemeColor(@AttrRes attribute: Int): Int {
        TypedValue().let {
            theme.resolveAttribute(attribute, it, true)
            return it.data
        }
    }

}