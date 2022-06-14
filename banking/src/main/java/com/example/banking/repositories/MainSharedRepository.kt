package com.example.banking.repositories

import com.example.banking.models.RatesResponseModel
import com.example.banking.net.ApiResultCallBack
import com.example.banking.net.DataSource
import com.example.banking.net.acbaResponse

interface MainSharedRepository {
    suspend fun getRates(
        apiResultCallBack: ApiResultCallBack<RatesResponseModel?>,
        showLoader: Boolean
    )
}

class MainSharedRepositoryImpl(private val dataSource: DataSource) : MainSharedRepository {

    override suspend fun getRates(
        apiResultCallBack: ApiResultCallBack<RatesResponseModel?>,
        showLoader: Boolean
    ) {
        acbaResponse(apiResultCallBack, showLoader) {
            dataSource.rates()
        }
    }
}