package com.muhammhassan.core.repository

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<ApiResponse<List<Articles>>>

    fun addBookmarkNews(data: BookmarkEntity)
    fun removeBookmarkNews(title: String)
    fun getBookmarkedNews(): Flow<List<BookmarkEntity>>
    fun getSpecifiedData(title: String): Flow<BookmarkEntity?>
}