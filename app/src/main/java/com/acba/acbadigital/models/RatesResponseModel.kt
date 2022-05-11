package com.acba.acbadigital.models

data class RatesResponseModel(
    val rates: Rates?
)

data class Rates(
    val card: List<Card>?,
    val cash: List<Card>?,
    val cross: List<Card>?,
    val currencies: List<Currency>?,
    val last_update_date: String?,
    val non_cash: List<Card>?
)


data class Card(
    val Buy: String?,
    val CB: String?,
    val Currency: String?,
    val Sell: String?
)

data class Currency(
    val Key: String?,
    val Value: String?
)