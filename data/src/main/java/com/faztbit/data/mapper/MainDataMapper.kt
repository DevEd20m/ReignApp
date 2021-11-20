package com.faztbit.data.mapper

import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.database.model.HitsRemovedDb
import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.domain.models.HitsDomain

interface MainDataMapper {
    suspend fun mapHitsResponseToDomain(response: HitsHeaderResponse): List<HitsDomain>
    suspend fun mapHitsDbToDomain(response: List<HitsDb>): List<HitsDomain>
    suspend fun mapHitsDomainToDb(response: List<HitsDomain>): List<HitsDb>
    suspend fun mapHitsRemovedDbToDomain(response: List<HitsRemovedDb>): List<HitsDomain>

}