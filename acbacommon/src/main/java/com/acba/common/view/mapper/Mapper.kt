package com.acba.common.view.mapper

import com.acba.common.view.Rates
import com.acba.common.view.response.RateResponse

interface Mapper {
    fun toRatesModel(
        ratesModel: RateResponse
    ): Rates
}