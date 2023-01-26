package com.muhammhassan.domain.model

import android.os.Parcelable
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class NewsModel(
    val title: String,
    val desc: String?,
    val content: String?,
    val image: String?,
    val url: String?,
    val source: String?,
    val publishedAt: String
) : Parcelable {
    fun mapToBookmarkModel(): BookmarkEntity = BookmarkEntity(
        null,
        title,
        image,
        url,
        Date().time.toString(),
        desc,
        content,
        source,
        publishedAt
    )
}