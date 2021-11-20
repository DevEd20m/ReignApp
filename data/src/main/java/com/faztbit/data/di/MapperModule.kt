package com.faztbit.data.di

import com.faztbit.data.mapper.MainDataMapper
import com.faztbit.data.mapper.MainDataMapperImpl
import org.koin.dsl.module

val mapperDataModule = module {
    single<MainDataMapper> { MainDataMapperImpl() }
}