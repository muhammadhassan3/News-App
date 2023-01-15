package com.muhammhassan.core.api

import com.muhammhassan.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderAuthorization: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.request().newBuilder().addHeader("Authorization", BuildConfig.API_KEY)
        return chain.proceed(response.build())
    }
}