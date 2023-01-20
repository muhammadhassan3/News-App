package com.muhammhassan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammhassan.core.database.dao.BookmarkDao
import com.muhammhassan.core.database.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}