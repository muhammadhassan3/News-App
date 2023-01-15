package com.muhammhassan.core.repository

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.core.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val remote: RemoteDataSource): NewsRepository {
    override fun getNews(): Flow<ApiResponse<Articles>> {
        return remote.getNews()
    }
}