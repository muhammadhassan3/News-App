package com.muhammhassan.bookmark.di

import com.muhammhassan.bookmark.ui.viewmodel.BookmarkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {
    val bookmarkViewModelModule = module{
        viewModel { BookmarkViewModel(get()) }
    }

}