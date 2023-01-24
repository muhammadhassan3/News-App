package com.muhammhassan.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.usecase.DetailUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: DetailUseCase) : ViewModel() {
    private val _isBookmarked = MutableLiveData<Boolean>()
    private val isBookmarked: LiveData<Boolean> get() = _isBookmarked

    fun getIsBookmarked(title: String): LiveData<Boolean> {
        viewModelScope.launch {
            useCase.getSpecifiedData(title).collect {
                _isBookmarked.postValue(it)
            }
        }
        return isBookmarked
    }

    fun addBookmarkedData(data: NewsModel) {
        useCase.addBookmarkedData(data)
    }

    fun removeBookmarkedData(data: NewsModel) {
        useCase.removeBookmarkedData(data)
    }
}