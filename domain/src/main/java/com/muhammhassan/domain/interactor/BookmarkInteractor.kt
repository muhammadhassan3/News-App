package com.muhammhassan.domain.interactor

import com.muhammhassan.core.repository.NewsRepository
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.usecase.BookmarkUseCase
import com.muhammhassan.domain.utils.Mapper.mapBookmarkToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkInteractor(private val repository: NewsRepository): BookmarkUseCase {
    override fun getData(): Flow<List<NewsModel>> {
        return repository.getBookmarkedNews().map { it.mapBookmarkToDomain() }
    }

    companion object{
        @Volatile
        private var INSTANCE: BookmarkInteractor? = null

        fun getInstance(repository: NewsRepository): BookmarkInteractor = INSTANCE ?: synchronized(this){
            val instance = BookmarkInteractor(repository)
            INSTANCE = instance
            instance
        }
    }
}