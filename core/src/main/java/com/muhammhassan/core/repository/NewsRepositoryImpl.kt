package com.muhammhassan.core.repository

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.core.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val remote: RemoteDataSource): NewsRepository {
    override fun getNews(): Flow<ApiResponse<List<Articles>>> {
        return remote.getNews()
    }

    companion object{
        @Volatile
        private var INSTANCE: NewsRepositoryImpl? = null

        fun getInstance(remote: RemoteDataSource) = INSTANCE ?: synchronized(this){
            val instance = NewsRepositoryImpl(remote)
            INSTANCE = instance
            instance
        }
    }
}