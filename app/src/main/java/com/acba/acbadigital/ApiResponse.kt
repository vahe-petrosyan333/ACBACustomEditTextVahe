package com.acba.acbadigital

sealed class ApiResponse<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    data class Success<T>(val mData: T) : ApiResponse<T>(mData, null)

    data class Error<T>(val mException: Exception) : ApiResponse<T>(null, mException)
}
