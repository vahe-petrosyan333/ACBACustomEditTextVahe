package com.example.common.repositories

import com.example.common.net.ApiResultCallBack
import com.example.common.net.DataSource

interface MainSharedRepository {
//    suspend fun getRates(
//        apiResultCallBack: ApiResultCallBack<RatesResponseModel?>,
//        showLoader: Boolean
//    )
}

class MainSharedRepositoryImpl(private val dataSource: DataSource) : MainSharedRepository {
//
//    override suspend fun getRates(
//        apiResultCallBack: ApiResultCallBack<null?>,
//        showLoader: Boolean
//    ) {
//        acbaResponse(apiResultCallBack, showLoader) {
//            dataSource.rates()
//        }
//    }
}