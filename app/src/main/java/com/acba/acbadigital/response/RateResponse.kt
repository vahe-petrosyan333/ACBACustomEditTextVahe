package com.acba.acbadigital.response

import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("Buy")
    val buy: String?,
    @SerializedName("Sell")
    val sell: String?,
    @SerializedName("CB")
    val CB: String?,
    @SerializedName("Currency")
    val currency: String?,
)
