package com.muhammhassan.core.datasource

import com.muhammhassan.core.database.NewsDatabase
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

class LocalDataSourceImpl(db: NewsDatabase) : LocalDataSource {
    private val dao = db.bookmarkDao()
    private val executor = Executors.newSingleThreadExecutor()
    override fun getData(): Flow<List<BookmarkEntity>> {
        return dao.findBookmarkedBooks()
    }

    override fun addData(data: BookmarkEntity) {
        executor.execute {
            dao.insertBookmarkedItem(data)
        }
    }

    override fun deleteData(title: String) {
        executor.execute {
            dao.deleteBookmarkedItem(title)
        }
    }

    override fun getSpecifiedData(title: String): Flow<BookmarkEntity?> {
        return dao.getBookmarkedItem(title)
    }
}