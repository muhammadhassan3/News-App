package com.muhammhassan.domain.utils

import com.muhammhassan.domain.interactor.MainInteractor
import com.muhammhassan.domain.usecase.MainUseCase
import org.koin.dsl.module

object Module {
    val useCaseModule = module {
        single<MainUseCase> { MainInteractor.getInstance(get()) }
    }
}