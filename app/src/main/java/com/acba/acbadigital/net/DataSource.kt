package com.acba.acbadigital.net


import com.acba.acbadigital.models.Rates
import retrofit2.Response
import retrofit2.http.GET

interface DataSource {
    @GET("rates")
    suspend fun rates(): Response<Rates>
}