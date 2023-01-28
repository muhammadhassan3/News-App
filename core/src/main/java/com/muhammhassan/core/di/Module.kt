package com.muhammhassan.core.di

import android.content.Context
import androidx.room.Room
import com.muhammhassan.core.BuildConfig
import com.muhammhassan.core.api.ApiClient
import com.muhammhassan.core.api.ApiClient.Companion.getNewsService
import com.muhammhassan.core.database.NewsDatabase
import com.muhammhassan.core.datasource.LocalDataSource
import com.muhammhassan.core.datasource.LocalDataSourceImpl
import com.muhammhassan.core.datasource.RemoteDataSource
import com.muhammhassan.core.datasource.RemoteDataSourceImpl
import com.muhammhassan.core.repository.NewsRepository
import com.muhammhassan.core.repository.NewsRepositoryImpl
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object Module {
    val provideApiClient = module {
        single { ApiClient.getClient().getNewsService() }
    }

    val provideDataSource = module {
        single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
        single<LocalDataSource> { LocalDataSourceImpl(get()) }
    }

    val provideRepository = module {
        single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    }

    val provideDatabase = module {
        fun dbInstance(context: Context): NewsDatabase {
            val password = SQLiteDatabase.getBytes(BuildConfig.DB_PASS.toCharArray())
            val factory = SupportFactory(password)
            return Room.databaseBuilder(context, NewsDatabase::class.java, "news_db")
                .fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }
        single { dbInstance(androidContext()) }
    }
}