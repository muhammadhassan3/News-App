package com.muhammhassan.core.utils

import okio.IOException
import org.json.JSONObject
import retrofit2.Response

object Utils {
    fun <T> parseError(response: Response<T>, messageKey: String = "message"): String{
        return try{
            response.errorBody()?.string().runCatching {
                this?.let {
                    JSONObject(it).getString(messageKey)
                }
            }.getOrNull() ?: response.message()
        }catch (e: IOException){
            e.message.toString()
        }
    }
}