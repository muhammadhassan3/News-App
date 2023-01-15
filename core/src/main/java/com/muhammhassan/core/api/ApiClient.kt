package com.muhammhassan.core.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class ApiClient {
    companion object {
        private val client = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).addInterceptor(HeaderAuthorization()).build()

        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getClient(): Retrofit =
            INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://newsapi.org/")
                    .build()
                INSTANCE = instance
                instance
            }

        fun Retrofit.getNewsService(): ApiInterface{
            return this.create(ApiInterface::class.java)
        }
    }
}