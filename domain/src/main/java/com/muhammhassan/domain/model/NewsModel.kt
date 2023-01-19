package com.muhammhassan.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val title: String,
    val desc: String?,
    val content: String?,
    val image: String?,
    val url: String?
): Parcelable