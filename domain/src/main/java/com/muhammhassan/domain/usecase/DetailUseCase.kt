package com.muhammhassan.domain.usecase

import com.muhammhassan.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    fun addBookmarkedData(news: NewsModel)
    fun removeBookmarkedData(news: NewsModel)
    fun getSpecifiedData(title: String): Flow<Boolean>
}