package com.faztbit.data.di

import com.faztbit.data.source.services.retrofit.ApiClient
import com.faztbit.data.utils.ConnectionUtils
import com.faztbit.data.utils.ConnectionUtilsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val networkModule = module {
    single { ApiClient.endPoint }
    single<ConnectionUtils> {
        ConnectionUtilsImpl(androidContext())
    }

}



