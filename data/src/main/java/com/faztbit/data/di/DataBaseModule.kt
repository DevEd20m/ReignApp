package com.faztbit.data.di

import com.faztbit.data.source.database.ReignDataBaseObject
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        ReignDataBaseObject.provideLocalDataBase(
            androidApplication(),
            //get(named("Migration"))
        )
    }
    //Dao
    single {
        ReignDataBaseObject.provideStatsManagementDao(get())
    }
    single {
        ReignDataBaseObject.provideScheduledVisitDao(get())
    }
}