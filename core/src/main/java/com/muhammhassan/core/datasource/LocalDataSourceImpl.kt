package com.muhammhassan.core.datasource

import com.muhammhassan.core.database.dao.BookmarkDao
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(val dao: BookmarkDao): LocalDataSource {
    override fun getData(): Flow<List<BookmarkEntity>> {
        return dao.findBookmarkedBooks()
    }

    override fun addData(data: BookmarkEntity) {
        dao.insertBookmarkedItem(data)
    }

    override fun deleteData(title: String) {
        dao.deleteBookmarkedItem(title)
    }

    override fun getSpecifiedData(title: String): Flow<BookmarkEntity?> {
        return dao.getBookmarkedItem(title)
    }

    companion object{
        @Volatile
        private var INSTANCE: LocalDataSourceImpl? = null

        fun getInstance(dao: BookmarkDao): LocalDataSourceImpl = INSTANCE ?: synchronized(this){
            val instance = LocalDataSourceImpl(dao)
            INSTANCE = instance
            instance
        }
    }
}