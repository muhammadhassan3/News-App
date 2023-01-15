package com.muhammhassan.core.repository

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<ApiResponse<Articles>>
}