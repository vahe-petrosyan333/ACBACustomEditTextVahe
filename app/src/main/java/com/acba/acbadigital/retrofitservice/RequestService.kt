package com.acba.acbadigital.retrofitservice


import com.acba.acbadigital.models.RatesResponseModel
import com.acba.acbadigital.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface RequestService {
    @GET("rates")
    suspend fun rates(): Response<ResponseModel<RatesResponseModel>>
}