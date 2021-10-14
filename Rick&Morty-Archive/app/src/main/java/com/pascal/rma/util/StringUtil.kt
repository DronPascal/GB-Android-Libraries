package com.pascal.rma.util

/**
 * Created by dronpascal on 12.10.2021.
 */

object StringUtil {

    /**
     * Extension function that extracts [Id] from String like
     * [https://rickandmortyapi.com/api/character/30]
     */
    fun String?.unitId(): Int? =
        if (this.isNullOrBlank()) null
        else this.split("/").last().toInt()

    /**
     * Extension function that extracts [Id] from String like
     * [https://rickandmortyapi.com/api/character/?page=1]
     */
    fun String?.pageId(): Int? =
        if (this.isNullOrBlank()) {
            null
        } else {
            val regex = """page=\d+""".toRegex()
            val query = regex.find(this)?.value
            val page = query?.split("=")?.last()?.toInt()
            page
        }

}