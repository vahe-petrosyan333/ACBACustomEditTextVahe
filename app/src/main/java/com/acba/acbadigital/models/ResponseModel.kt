package com.acba.acbadigital.models

data class ResponseModel<out T>(
    val Description: String,
    val Result: T,
    val ResultCode: Int,
    val ResultCodeDescription: String
)