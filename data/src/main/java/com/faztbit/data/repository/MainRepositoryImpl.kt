package com.faztbit.data.repository

import com.faztbit.data.mapper.MainDataMapper
import com.faztbit.data.source.services.dataSource.MainServiceDataSource
import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.repository.MainRepository
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

class MainRepositoryImpl(
    private val service: MainServiceDataSource,
    private val mapper: MainDataMapper
) : MainRepository {
    override suspend fun fetchHitsByQuery(query: String?): Either<Failure, List<HitsDomain>> {
        return when (val response = service.fetchHitsByQuery(query)) {
            is Either.Success -> Either.Success(mapper.mapHitsResponseToDomain(response.success))
            is Either.Error -> Either.Error(response.error)
            else -> Either.Error(Failure.ErrorNotMapped())
        }
    }

}