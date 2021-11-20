package com.faztbit.data.source.services.dataSource

import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

interface MainServiceDataSource {
    suspend fun fetchHitsByQuery(query: String?): Either<Failure, HitsHeaderResponse>
}