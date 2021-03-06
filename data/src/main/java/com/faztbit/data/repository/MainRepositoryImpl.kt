package com.faztbit.data.repository

import com.faztbit.data.mapper.MainDataMapper
import com.faztbit.data.source.database.dataSource.MainDataBaseDataSource
import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.database.model.HitsRemovedDb
import com.faztbit.data.source.services.dataSource.MainServiceDataSource
import com.faztbit.data.utils.ConnectionUtils
import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.repository.MainRepository
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

class MainRepositoryImpl(
    private val service: MainServiceDataSource,
    private val database: MainDataBaseDataSource,
    private val mapper: MainDataMapper,
    private val connection: ConnectionUtils,
) : MainRepository {

    override suspend fun fetchHitsByQuery(query: String?): Either<Failure, List<HitsDomain>> {
        return if (connection.isNetworkAvailable()) {
            when (val response = service.fetchHitsByQuery(query)) {
                is Either.Success -> {
                    val data = mapper.mapHitsResponseToDomain(response.success)
                    saveHitsInDataBase(data)
                    Either.Success(data)
                }
                is Either.Error -> Either.Error(response.error)
            }
        } else {
            getHitsSaved()
        }
    }

    override suspend fun getHitsSaved(): Either<Failure, List<HitsDomain>> {
        return when (val response = database.getHitsSaved()) {
            is Either.Success -> {
                if (validateSizeDataInDataBase(response.success)) {
                    Either.Success(mapper.mapHitsDbToDomain(response.success))
                } else Either.Error(Failure.ResourceNotFound())

            }
            is Either.Error -> Either.Error(response.error)
        }
    }

    private fun validateSizeDataInDataBase(data: List<HitsDb>): Boolean {
        return data.isNotEmpty()
    }

    override suspend fun saveHitsInDataBase(data: List<HitsDomain>): Either<Failure, Unit> {
        return when (val response = database.saveHits(mapper.mapHitsDomainToDb(data))) {
            is Either.Error -> Either.Error(response.error)
            is Either.Success -> Either.Success(Unit)
        }
    }

    override suspend fun removeHitsFromDataSource(data: HitsDomain): Either<Failure, Unit> {
        return when (val response = database.saveHitsRemoved(
            HitsRemovedDb(
                data.objectId,
                data.title,
                data.author,
                data.createAt, data.url
            )
        )) {
            is Either.Error -> Either.Error(response.error)
            is Either.Success -> Either.Success(Unit)
        }
    }

    override suspend fun getHitsRemoved(): Either<Failure, List<HitsDomain>> {
        return when (val response = database.getHitsRemoved()) {
            is Either.Error -> Either.Error(response.error)
            is Either.Success -> Either.Success(mapper.mapHitsRemovedDbToDomain(response.success))
        }
    }

}