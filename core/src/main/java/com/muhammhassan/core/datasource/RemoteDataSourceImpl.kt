package com.muhammhassan.core.datasource

import com.muhammhassan.core.api.ApiInterface
import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(val api: ApiInterface) : RemoteDataSource{
    override fun getNews(): Flow<ApiResponse<Articles>> = flow {
        emit(ApiResponse.loading())
        try{
            val response = api.getNews()
            if(response.isSuccessful){
                val data = response.body()?.articles
                if(data != null)emit(ApiResponse.success(data))
                else emit(ApiResponse.noData())
            }else emit(ApiResponse.error(response.body()?.message.toString()))
        }catch (e: Exception){
            emit(ApiResponse.error(e.message.toString()))
        }

    }

    companion object{
        @Volatile
        private var INSTANCE: RemoteDataSourceImpl? = null

        fun getInstance(api: ApiInterface): RemoteDataSourceImpl = INSTANCE ?: synchronized(this){
            val instance = RemoteDataSourceImpl(api)
            INSTANCE = instance
            instance
        }
    }
}