package com.faztbit.data.source.services.response

import com.google.gson.annotations.SerializedName


data class HitsHeaderResponse(
    @SerializedName("hits")
    val hits: List<HitsMainResponse>
)

data class HitsMainResponse(
    @SerializedName("objectID")
    val objectId: String,
    @SerializedName("story_title")
    val title: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("created_at")
    val createAt: String?,
    @SerializedName("story_url")
    val url: String?
)