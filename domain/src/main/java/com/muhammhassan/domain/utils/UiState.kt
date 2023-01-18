package com.muhammhassan.domain.utils

sealed class UiState {
    data class Success<T>(val data: T): UiState()
    data class Error(val errorMessage: String?): UiState()
    object NoData: UiState()
    object Loading: UiState()
}