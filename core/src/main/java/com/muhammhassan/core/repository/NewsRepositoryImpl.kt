package com.muhammhassan.core.repository

import com.muhammhassan.core.api.ApiResponse
import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.core.database.entity.BookmarkEntity
import com.muhammhassan.core.datasource.LocalDataSource
import com.muhammhassan.core.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val remote: RemoteDataSource, private val local: LocalDataSource): NewsRepository {
    override fun getNews(): Flow<ApiResponse<List<Articles>>> {
        return remote.getNews()
    }

    override fun addBookmarkNews(data: BookmarkEntity) {
        local.addData(data)
    }

    override fun removeBookmarkNews(title: String) {
        local.deleteData(title)
    }

    override fun getBookmarkedNews(): Flow<List<BookmarkEntity>> {
        return local.getData()
    }

    override fun getSpecifiedData(title: String): Flow<BookmarkEntity?> {
        return local.getSpecifiedData(title)
    }
}