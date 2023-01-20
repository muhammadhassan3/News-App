package com.muhammhassan.domain.model

import android.os.Parcelable
import com.muhammhassan.core.database.entity.BookmarkEntity
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class NewsModel(
    val title: String,
    val desc: String?,
    val content: String?,
    val image: String?,
    val url: String?
): Parcelable{
    fun mapToBookmarkModel(): BookmarkEntity = BookmarkEntity(null, title, image, url, Date().time.toString(), desc, content)
}