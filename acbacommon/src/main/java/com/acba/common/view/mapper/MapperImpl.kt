package com.acba.common.view.mapper

import com.acba.common.view.Rates
import com.acba.common.view.response.RateResponse

object MapperImpl:Mapper {
    override fun toRatesModel(ratesModel: RateResponse): Rates {
        return Rates(
            buy = ratesModel.buy,
            sell = ratesModel.sell,
            CB = ratesModel.CB,
            currency = ratesModel.currency
        )
    }
}