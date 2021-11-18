package com.faztbit.data.source.services.retrofit

import com.faztbit.data.BuildConfig
import com.faztbit.data.utils.Constants

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private fun addInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(addInterceptor())
        .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
        .readTimeout(Constants.readTimeout, TimeUnit.SECONDS)
        .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaseURL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val endPoint: EndPoints by lazy { retrofit().create(EndPoints::class.java) }
}