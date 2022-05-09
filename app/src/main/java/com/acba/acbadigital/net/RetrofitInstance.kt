package com.acba.acbadigital.net

import com.acba.acbadigital.urls.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
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

    fun getRequestService(): DataSource {
        return retrofit.create(DataSource::class.java)
    }
}