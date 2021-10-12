package com.pascal.rma.util

/**
 * Created by dronpascal on 12.10.2021.
 */
fun String?.apiId(): Int = this?.split("/")?.last()?.toInt() ?: 0