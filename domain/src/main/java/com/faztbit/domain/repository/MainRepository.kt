package com.faztbit.domain.repository

import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure

interface MainRepository {
    suspend fun fetchHitsByQuery(query:String?): Either<Failure, List<HitsDomain>>
}