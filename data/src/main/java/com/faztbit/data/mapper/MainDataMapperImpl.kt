package com.faztbit.data.mapper

import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.domain.models.HitsDomain

class MainDataMapperImpl : MainDataMapper {
    override suspend fun mapHitsResponseToDomain(response: HitsHeaderResponse): List<HitsDomain> {
        TODO("Not yet implemented")
    }


}