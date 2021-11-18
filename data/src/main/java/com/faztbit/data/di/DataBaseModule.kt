package com.faztbit.data.di

import android.app.Application
import androidx.room.Room
import com.faztbit.data.source.database.ReignDataBase
import com.faztbit.data.source.database.dao.HitsDao
import com.faztbit.data.source.database.dao.HitsRemovedDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    fun provideDatabase(application: Application): ReignDataBase {
        return Room.databaseBuilder(application, ReignDataBase::class.java, "ReignDataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideHitsRemovedDao(database: ReignDataBase): HitsRemovedDao {
        return database.hitsRemovedDao()
    }

    fun provideHitsDao(database: ReignDataBase): HitsDao {
        return database.hitsDao()
    }
    single { provideDatabase(androidApplication()) }
    single { provideHitsDao(get()) }
    single { provideHitsRemovedDao(get()) }
}