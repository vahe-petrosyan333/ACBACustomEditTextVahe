package com.acba.acbadigital.response

import com.google.gson.annotations.SerializedName

open class BaseResponseModel<T>(
    @SerializedName("Description")
    val description: String? = null,
    @SerializedName("ResultCode")
    val resultCode: Int? = null,
    @SerializedName("resultCodeDescription")
    val ResultCodeDescription: String? = null,
    @SerializedName("Result")
    val result: T? = null
)