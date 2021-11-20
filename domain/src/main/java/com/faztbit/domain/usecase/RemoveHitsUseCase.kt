package com.faztbit.domain.usecase

import com.faztbit.domain.base.BaseUseCase
import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.repository.MainRepository
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

class RemoveHitsUseCase(private val repository: MainRepository) : BaseUseCase<Unit, HitsDomain>() {
    override suspend fun run(params: HitsDomain): Either<Failure, Unit> {
        return repository.removeHitsFromDataSource(params)
    }
}