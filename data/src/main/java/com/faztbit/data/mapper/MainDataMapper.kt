package com.faztbit.data.mapper

import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.domain.models.HitsDomain

interface MainDataMapper {
    suspend fun mapHitsResponseToDomain(response: HitsHeaderResponse): List<HitsDomain>

}