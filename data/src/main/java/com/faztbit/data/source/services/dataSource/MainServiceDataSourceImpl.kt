package com.faztbit.data.source.services.dataSource

import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.data.source.services.retrofit.BaseClient
import com.faztbit.data.source.services.retrofit.EndPoints
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

class MainServiceDataSourceImpl(
    private val endPoints: EndPoints,
) : MainServiceDataSource, BaseClient() {

    override suspend fun fetchHitsByQuery(query: String?): Either<Failure, HitsHeaderResponse> =
        callService {
            endPoints.fetchHitsByQuery(query)
        }

}