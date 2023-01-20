package com.muhammhassan.core.datasource

import com.muhammhassan.core.database.dao.BookmarkDao
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(val dao: BookmarkDao): LocalDataSource {
    override fun getData(): Flow<List<BookmarkEntity>> {
        TODO("Not yet implemented")
    }

    override fun addData(data: BookmarkEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteData(data: BookmarkEntity) {
        TODO("Not yet implemented")
    }
}