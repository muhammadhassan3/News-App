package com.muhammhassan.domain.utils

import com.muhammhassan.core.api.model.Articles
import com.muhammhassan.domain.model.NewsModel

object Mapper {
    fun List<Articles>?.mapToDomain() = this?.map { articles ->
        NewsModel(
            title = articles.title,
            desc = articles.description,
            content = articles.content,
            image = articles.urlToImage,
            url = articles.url
        )
    }
}