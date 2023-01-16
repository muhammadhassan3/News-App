package com.muhammhassan.core.datasource

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getNews(): Flow<ApiResponse<List<Articles>>>
}