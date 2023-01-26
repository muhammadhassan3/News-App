package com.muhammhassan.domain.utils

import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.core.database.entity.BookmarkEntity
import com.muhammhassan.domain.model.NewsModel

object Mapper {
    fun List<Articles>?.mapArticleToDomain() = this?.map { articles ->
        NewsModel(
            title = articles.title,
            desc = articles.description,
            content = articles.content,
            image = articles.urlToImage,
            url = articles.url,
            source = articles.source.name,
            publishedAt = Utils.parseDate(articles.publishedAt)
        )
    }

    fun List<BookmarkEntity>.mapBookmarkToDomain() = this.map {item ->
        NewsModel(
            title = item.title!!,
            desc = item.desc,
            content = item.content,
            image = item.imageUrl,
            url = item.url,
            source = item.source,
            publishedAt = item.publishedAt
        )
    }
}