package com.faztbit.data.source.services.response

import com.google.gson.annotations.SerializedName


data class HitsHeaderResponse(
    @SerializedName("hits")
    val hits: List<HitsMainResponse>
)

data class HitsMainResponse(
    @SerializedName("objectID")
    val objectId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("created_at")
    val createAt: String?
)