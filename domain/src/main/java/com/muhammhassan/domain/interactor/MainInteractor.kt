package com.muhammhassan.domain.interactor

import com.muhammhassan.core.api.Status
import com.muhammhassan.core.repository.NewsRepository
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.usecase.MainUseCase
import com.muhammhassan.domain.utils.Mapper.mapToDomain
import com.muhammhassan.domain.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainInteractor(private val repository: NewsRepository) : MainUseCase {
    override fun getNews(): Flow<UiState> {
        return repository.getNews().map {
                when (it.status) {
                    Status.LOADING -> UiState.Loading
                    Status.ERROR -> UiState.Error(it.errorMessage)
                    Status.SUCCESS -> UiState.Success(it.data?.mapToDomain())
                    Status.NO_DATA -> UiState.NoData
                }
            }

    }

    companion object {
        @Volatile
        private var INSTANCE: MainInteractor? = null

        fun getInstance(repository: NewsRepository) = INSTANCE ?: synchronized(this) {
            val instance = MainInteractor(repository)
            INSTANCE = instance
            instance
        }
    }
}