package com.muhammhassan.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "desc")
    val desc: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "source")
    val source: String?,
    @ColumnInfo(name = "published_at")
    val publishedAt: String
)