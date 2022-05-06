package com.acba.acbadigital.models

data class Rates(
    val card: List<Card>,
) {
    data class Card(
        val Buy: String,
        val CB: String,
        val Currency: String,
        val Sell: String
    )
}