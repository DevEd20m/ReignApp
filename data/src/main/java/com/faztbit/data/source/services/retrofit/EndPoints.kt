package com.faztbit.data.source.services.retrofit

import com.faztbit.data.source.services.response.HitsHeaderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {

    @GET("search_by_date")
    suspend fun fetchHitsByQuery(@Query("query") query: String?): Response<HitsHeaderResponse>

}