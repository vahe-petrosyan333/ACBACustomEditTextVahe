package com.acba.acbadigital.net


import com.acba.acbadigital.models.RatesResponseModel
import com.acba.acbadigital.response.BaseResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface DataSource {
    @GET("rates")
    suspend fun rates(): Response<BaseResponseModel<RatesResponseModel>>
}