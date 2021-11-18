package com.faztbit.data.source.database.dataSource

import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

interface MainDataBaseDataSource {

    suspend fun getHitsSaved(): Either<Failure, List<HitsDb>>
}