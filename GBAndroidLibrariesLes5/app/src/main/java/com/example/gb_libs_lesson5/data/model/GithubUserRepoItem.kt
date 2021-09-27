package com.example.gb_libs_lesson5.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserRepoItem(
    @Expose
    @SerializedName("forks_count")
    val forksCount: String? = null,

    @Expose
    @SerializedName("full_name")
    val fullName: String? = null,
) : Parcelable