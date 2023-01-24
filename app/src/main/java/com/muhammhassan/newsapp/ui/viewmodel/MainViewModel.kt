package com.muhammhassan.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammhassan.domain.usecase.MainUseCase
import com.muhammhassan.domain.utils.UiState
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase): ViewModel() {
    private val _data = MutableLiveData<UiState>()
    val data: LiveData<UiState> get() = _data

    fun getData(){
        viewModelScope.launch {
            useCase.getNews().collect{
                _data.postValue(it)
            }
        }
    }
}