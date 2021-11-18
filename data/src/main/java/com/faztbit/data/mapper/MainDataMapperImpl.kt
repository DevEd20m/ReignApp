package com.faztbit.data.mapper

import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.services.response.HitsHeaderResponse
import com.faztbit.domain.models.HitsDomain

class MainDataMapperImpl : MainDataMapper {
    override suspend fun mapHitsResponseToDomain(response: HitsHeaderResponse): List<HitsDomain> {
        return response.hits.map {
            HitsDomain(it.objectId, it.title, it.author, it.createAt)
        }
    }

    override suspend fun mapHitsDbToDomain(response: List<HitsDb>): List<HitsDomain> {
        return response.map {
            HitsDomain(it.objectId, it.title, it.author, it.createAt)
        }
    }


}