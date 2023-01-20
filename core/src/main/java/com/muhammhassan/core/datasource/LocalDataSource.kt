package com.muhammhassan.core.datasource

import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getData(): Flow<List<BookmarkEntity>>
    fun addData(data: BookmarkEntity)
    fun deleteData(title: String)
    fun getSpecifiedData(title: String): Flow<BookmarkEntity?>
}