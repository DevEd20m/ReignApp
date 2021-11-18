package com.faztbit.domain.usecase

import com.faztbit.domain.base.BaseUseCaseNoParams
import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.repository.MainRepository
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

class FetchHitsRemovedUseCase(private val repository: MainRepository) :
    BaseUseCaseNoParams<List<HitsDomain>>() {
    override suspend fun run(): Either<Failure, List<HitsDomain>> {
        return repository.getHitsRemoved()
    }
}