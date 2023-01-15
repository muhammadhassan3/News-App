package com.muhammhassan.core.api.model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: Articles?,
    val code: String?,
    val message: String?
)