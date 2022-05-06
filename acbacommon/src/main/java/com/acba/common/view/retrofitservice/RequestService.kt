package com.acba.common.view.retrofitservice

import com.acba.common.view.response.RateResponse
import retrofit2.http.POST

interface RequestService {
    @POST("rates")
    suspend fun rates(): RateResponse
}