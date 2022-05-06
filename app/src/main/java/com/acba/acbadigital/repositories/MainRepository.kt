package com.acba.acbadigital.repositories

import com.acba.acbadigital.ApiResponse
import com.acba.acbadigital.mapper.MapperImpl
import com.acba.acbadigital.models.Rates
import com.acba.acbadigital.retrofitservice.RetrofitInstance


object MainRepository {

    suspend fun search(): ApiResponse<Rates> {
        return try {
            val response = RetrofitInstance
                .getRequestService().rates()
            if (response.isSuccessful)
                response.body()?.let { ApiResponse.Success(MapperImpl.map(it.Result)) }
                    ?: ApiResponse.Error(mException = Exception())
            else ApiResponse.Error(mException = Exception())
        } catch (e: java.lang.Exception) {
            ApiResponse.Error(mException = e)
        }
    }
}