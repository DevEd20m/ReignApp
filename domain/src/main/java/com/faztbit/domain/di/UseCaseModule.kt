package com.faztbit.domain.di

import com.faztbit.domain.usecase.FetchHitsRemovedUseCase
import com.faztbit.domain.usecase.FetchHitsUseCase
import com.faztbit.domain.usecase.RemoveHitsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { FetchHitsUseCase(get()) }
    factory { FetchHitsRemovedUseCase(get()) }
    factory { RemoveHitsUseCase(get()) }

}