package com.muhammhassan.domain.interactor

import com.muhammhassan.core.api.Status
import com.muhammhassan.core.repository.NewsRepository
import com.muhammhassan.domain.usecase.MainUseCase
import com.muhammhassan.domain.utils.Mapper.mapToDomain
import com.muhammhassan.domain.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainInteractor(private val repository: NewsRepository) : MainUseCase {
    override fun getNews(): Flow<UiState> {
        return flow {
            repository.getNews().collect {
                when (it.status) {
                    Status.LOADING -> emit(UiState.Loading)
                    Status.ERROR -> emit(UiState.Error(it.errorMessage!!))
                    Status.SUCCESS -> emit(UiState.Success(it.data?.mapToDomain()))
                    Status.NO_DATA -> emit(UiState.NoData)
                }
            }
        }
    }
}