package com.muhammhassan.core.api.model

import java.util.*

data class Articles(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String
)
