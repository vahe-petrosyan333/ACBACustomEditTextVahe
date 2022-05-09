package com.acba.acbadigital.repositories

import com.acba.acbadigital.models.Rates
import com.acba.acbadigital.net.ApiResultCallBack
import com.acba.acbadigital.net.DataSource
import com.acba.acbadigital.net.acbaResponse

interface MainSharedRepository {
    suspend fun getRates(apiResultCallBack: ApiResultCallBack<Rates?>, showLoader: Boolean)
}

class MainSharedRepositoryImpl(private val dataSource: DataSource) : MainSharedRepository {

    override suspend fun getRates(apiResultCallBack: ApiResultCallBack<Rates?>, showLoader: Boolean) {
        acbaResponse(apiResultCallBack, showLoader) {
            dataSource.rates()
        }
    }
}