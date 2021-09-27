package com.example.gb_libs_lesson6.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @Expose
    val login: String? = null,

    @Expose
    val id: Long? = null,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String? = null,
) : Parcelable