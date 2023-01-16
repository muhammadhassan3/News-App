package com.muhammhassan.core.di

import com.muhammhassan.core.api.ApiClient
import com.muhammhassan.core.api.ApiClient.Companion.getNewsService
import com.muhammhassan.core.datasource.RemoteDataSource
import com.muhammhassan.core.datasource.RemoteDataSourceImpl
import com.muhammhassan.core.repository.NewsRepository
import com.muhammhassan.core.repository.NewsRepositoryImpl
import org.koin.dsl.module

object Module {
    val provideApiClient = module {
        single { ApiClient.getClient().getNewsService() }
    }

    val provideDataSource = module {
        single<RemoteDataSource> { RemoteDataSourceImpl.getInstance(get()) }
    }

    val provideRepository = module {
        single<NewsRepository> { NewsRepositoryImpl.getInstance(get()) }
    }
}