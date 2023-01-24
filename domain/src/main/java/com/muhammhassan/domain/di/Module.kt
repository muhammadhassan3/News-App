package com.muhammhassan.domain.di

import com.muhammhassan.domain.interactor.BookmarkInteractor
import com.muhammhassan.domain.interactor.DetailInteractor
import com.muhammhassan.domain.interactor.MainInteractor
import com.muhammhassan.domain.usecase.BookmarkUseCase
import com.muhammhassan.domain.usecase.DetailUseCase
import com.muhammhassan.domain.usecase.MainUseCase
import org.koin.dsl.module

object Module {
    val useCaseModule = module {
        single<MainUseCase> { MainInteractor.getInstance(get()) }
        single<DetailUseCase> { DetailInteractor.getInstance(get()) }
        single<BookmarkUseCase> {BookmarkInteractor.getInstance(get())}
    }
}