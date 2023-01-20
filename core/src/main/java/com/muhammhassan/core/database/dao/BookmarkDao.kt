package com.muhammhassan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBookmarkedItem(bookmarkEntity: BookmarkEntity)

    @Query("select * from BookmarkEntity order by id")
    fun findBookmarkedBooks(): Flow<List<BookmarkEntity>>

    @Query("select * from BookmarkEntity where title = :title")
    fun getBookmarkedItem(title: String): Flow<BookmarkEntity?>

    @Query("delete from BookmarkEntity where title = :title")
    fun deleteBookmarkedItem(title: String)
}