package com.muhammhassan.bookmark.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.usecase.BookmarkUseCase
import kotlinx.coroutines.launch

class BookmarkViewModel(private val useCase: BookmarkUseCase): ViewModel() {
    private val _data = MutableLiveData<List<NewsModel>>()
    val data: LiveData<List<NewsModel>> get() = _data

    init {
        viewModelScope.launch {
            useCase.getData().collect{
                _data.postValue(it)
            }
        }
    }
}