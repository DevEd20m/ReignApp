package com.faztbit.data.di

import com.faztbit.data.repository.MainRepositoryImpl
import com.faztbit.domain.repository.MainRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get(), get()) }
}