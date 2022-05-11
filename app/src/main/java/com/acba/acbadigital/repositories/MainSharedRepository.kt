package com.acba.acbadigital.repositories

import com.acba.acbadigital.models.RatesResponseModel
import com.acba.acbadigital.net.ApiResultCallBack
import com.acba.acbadigital.net.DataSource
import com.acba.acbadigital.net.acbaResponse

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