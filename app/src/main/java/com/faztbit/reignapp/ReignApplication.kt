package com.faztbit.reignapp

import android.app.Application
import com.faztbit.data.di.*
import com.faztbit.domain.di.useCasesModule
import com.faztbit.reignapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ReignApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ReignApplication)
            modules(
                listOf(
                    viewModelModule,
                    useCasesModule,
                    networkModule,
                    dataBaseModule,
                    repositoryModule,
                    dataSourceModule,
                    mapperDataModule,
                )
            )
        }
    }
}