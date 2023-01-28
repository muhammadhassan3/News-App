package com.muhammhassan.core.api

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val hostname = "newsapi.org"
        private val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/7xmA6N1F1gp6ikj57Bg4DMG0jfUB+mZsEL4mZO0qbfU=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()

        private val client = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).addInterceptor(HeaderAuthorization()).certificatePinner(certificatePinner).build()

        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getClient(): Retrofit =
            INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                instance
            }

        fun Retrofit.getNewsService(): ApiInterface {
            return this.create(ApiInterface::class.java)
        }
    }
}