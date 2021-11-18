package com.faztbit.data.source.services.retrofit

import com.google.gson.annotations.SerializedName

data class BaseResponseError(
    @SerializedName("codeStatus")
    val codeStatus: Int?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?
)
