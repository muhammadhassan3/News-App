package com.muhammhassan.domain.usecase

import com.muhammhassan.domain.utils.UiState
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getNews(): Flow<UiState>
}