package com.muhammhassan.domain.usecase

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.utils.UiState
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getNews(): Flow<UiState>
}