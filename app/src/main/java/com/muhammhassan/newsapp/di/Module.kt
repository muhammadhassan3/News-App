package com.muhammhassan.newsapp.di

import com.muhammhassan.newsapp.ui.viewmodel.DetailViewModel
import com.muhammhassan.newsapp.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {
    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}