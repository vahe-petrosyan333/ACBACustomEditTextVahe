package com.example.common.base.net

import com.example.common.urls.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit() = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain
                        .request()
                        .newBuilder()
                        .addHeader("content_type", "application/json")
                        .build()
                )
            }
            .build()
    ).addConverterFactory(GsonConverterFactory.create())
    .build()