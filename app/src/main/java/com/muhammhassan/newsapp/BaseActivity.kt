package com.muhammhassan.newsapp

import android.app.Application
import com.muhammhassan.core.di.Module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseActivity: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                Module.provideApiClient,
                Module.provideDataSource,
                Module.provideRepository,
                com.muhammhassan.domain.di.Module.useCaseModule,
                com.muhammhassan.newsapp.utils.Module.viewModelModule
            ))
        }
    }
}