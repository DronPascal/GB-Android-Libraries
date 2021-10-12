package com.pascal.rma.util

/**
 * Created by dronpascal on 12.10.2021.
 */
fun String?.unitId(): Int? =
    if (this.isNullOrBlank()) null
    else this.split("/").last().toInt()

fun String?.pageId(): Int? =
    if (this.isNullOrBlank()) null
    else this.split("=").last().toInt()