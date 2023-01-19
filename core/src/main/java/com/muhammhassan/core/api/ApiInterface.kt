package com.muhammhassan.core.api

import com.muhammhassan.core.api.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("v2/everything?language=en&pageSize=30&q=kotlin")
    suspend fun getNews(): Response<NewsResponse>
}