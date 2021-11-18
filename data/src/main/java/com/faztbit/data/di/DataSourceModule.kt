package com.faztbit.data.di


import com.faztbit.data.source.services.dataSource.MainServiceDataSource
import com.faztbit.data.source.services.dataSource.MainServiceDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<MainServiceDataSource> { MainServiceDataSourceImpl(get()) }
}