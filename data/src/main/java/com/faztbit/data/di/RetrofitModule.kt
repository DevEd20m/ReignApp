package com.faztbit.data.di

import com.faztbit.data.source.services.retrofit.ApiClient
import org.koin.dsl.module


val networkModule = module {
    single { ApiClient.endPoint }
}



