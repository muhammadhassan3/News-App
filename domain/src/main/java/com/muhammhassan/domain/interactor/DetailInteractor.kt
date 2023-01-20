package com.muhammhassan.domain.interactor

import com.muhammhassan.core.database.entity.BookmarkEntity
import com.muhammhassan.core.repository.NewsRepository
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.usecase.DetailUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailInteractor(val repository: NewsRepository): DetailUseCase {
    override fun addBookmarkedData(news: NewsModel) {
        repository.addBookmarkNews(news.mapToBookmarkModel())
    }

    override fun removeBookmarkedData(news: NewsModel) {
        repository.removeBookmarkNews(news.title)
    }

    override fun getSpecifiedData(title: String): Flow<Boolean> {
        return repository.getSpecifiedData(title).map { value: BookmarkEntity? -> value != null }
    }

    companion object{
        @Volatile
        private var INSTANCE: DetailInteractor? = null

        fun getInstance(repository: NewsRepository): DetailInteractor = INSTANCE ?: synchronized(this){
            val instance = DetailInteractor(repository)
            INSTANCE = instance
            instance
        }
    }
}