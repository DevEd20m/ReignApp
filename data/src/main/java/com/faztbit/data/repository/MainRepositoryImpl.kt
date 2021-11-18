package com.faztbit.data.repository

import com.faztbit.data.mapper.MainDataMapper
import com.faztbit.data.source.database.dataSource.MainDataBaseDataSource
import com.faztbit.data.source.services.dataSource.MainServiceDataSource
import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.repository.MainRepository
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

class MainRepositoryImpl(
    private val service: MainServiceDataSource,
    private val database: MainDataBaseDataSource,
    private val mapper: MainDataMapper
) : MainRepository {
    override suspend fun fetchHitsByQuery(query: String?): Either<Failure, List<HitsDomain>> {
        return when (val response = service.fetchHitsByQuery(query)) {
            is Either.Success -> Either.Success(mapper.mapHitsResponseToDomain(response.success))
            is Either.Error -> Either.Error(response.error)
        }
    }

    override suspend fun getHitsSaved(query: String?): Either<Failure, List<HitsDomain>> {
        return when (val result = database.getHitsSaved()) {
            is Either.Error -> Either.Success(result.
            is Either.Success -> TODO()
        }
    }

}