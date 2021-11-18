package com.faztbit.data.source.database.dataSource

import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.database.model.HitsRemovedDb
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

interface MainDataBaseDataSource {

    suspend fun getHitsSaved(): Either<Failure, List<HitsDb>>
    suspend fun saveHits(data: List<HitsDb>): Either<Failure, Unit>
    suspend fun saveHitsRemoved(data: HitsRemovedDb): Either<Failure, Unit>
    suspend fun getHitsRemoved(): Either<Failure, List<HitsRemovedDb>>
}