package com.faztbit.domain.di

import com.faztbit.domain.usecase.FetchHitsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { FetchHitsUseCase(get()) }

}