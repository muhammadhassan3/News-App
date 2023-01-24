package com.muhammhassan.domain.usecase

import com.muhammhassan.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface BookmarkUseCase {
    fun getData(): Flow<List<NewsModel>>
}