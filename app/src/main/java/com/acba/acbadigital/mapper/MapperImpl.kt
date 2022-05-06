package com.acba.acbadigital.mapper

import com.acba.acbadigital.models.Rates
import com.acba.acbadigital.models.RatesResponseModel

object MapperImpl : Mapper<RatesResponseModel, Rates> {
    override fun map(model: RatesResponseModel): Rates {
        val rates = model.rates
        val cards = rates?.card ?: arrayListOf()
        val newCards = arrayListOf<Rates.Card>()
        for (card in cards) {
            newCards.add(
                Rates.Card(
                    card.Buy ?: "",
                    card.CB ?: "",
                    card.Currency ?: "",
                    card.Sell ?: ""
                )
            )
        }
        return Rates(card = newCards)
    }
}