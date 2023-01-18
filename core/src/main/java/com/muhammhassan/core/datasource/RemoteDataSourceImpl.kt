package com.muhammhassan.core.datasource

import com.muhammhassan.core.api.ApiInterface
import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.core.utils.Utils.parseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(val api: ApiInterface) : RemoteDataSource {
    override fun getNews(): Flow<ApiResponse<List<Articles>>> = flow {
        emit(ApiResponse.loading())
        val response = api.getNews()
        if (response.isSuccessful) {
            val data = response.body()?.articles
            if (data != null)
                emit(ApiResponse.success(data))
            else emit(ApiResponse.noData())
        } else emit(ApiResponse.error(parseError(response)))
    }.catch {
        it.printStackTrace()
        emit(ApiResponse.error(it.message.toString()))
    }

    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSourceImpl? = null

        fun getInstance(api: ApiInterface): RemoteDataSourceImpl = INSTANCE ?: synchronized(this) {
            val instance = RemoteDataSourceImpl(api)
            INSTANCE = instance
            instance
        }
    }
}