package com.muhammhassan.core.api

class ApiResponse<out T>(status: Status, data: T? = null, errorMessage: String? = null) {
    companion object{
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(status = Status.SUCCESS, data = data)
        fun <T> error(errorMessage: String): ApiResponse<T> = ApiResponse(status = Status.ERROR)
        fun <T> loading(): ApiResponse<T> = ApiResponse(status = Status.LOADING)
        fun <T> noData(): ApiResponse<T> = ApiResponse(status = Status.NO_DATA)
    }
}

enum class Status {
    LOADING, ERROR, SUCCESS, NO_DATA
}