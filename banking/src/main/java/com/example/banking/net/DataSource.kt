package com.example.banking.net

import com.example.banking.models.RatesResponseModel
import com.example.banking.response.BaseResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface DataSource {
    @GET("rates")
    suspend fun rates(): Response<BaseResponseModel<RatesResponseModel>>

}