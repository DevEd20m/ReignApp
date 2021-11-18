package com.faztbit.data.source.services.retrofit

import com.google.gson.annotations.SerializedName

data class BaseResponse<Model>(
    @SerializedName("codeStatus")
    val codeStatus: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("content")
    val data: Model
) {
    override fun toString(): String {
        return "BaseResponse{" +
                "success=" + success +
                ", message=" + message +
                ", body=" + data +
                '}'
    }
}