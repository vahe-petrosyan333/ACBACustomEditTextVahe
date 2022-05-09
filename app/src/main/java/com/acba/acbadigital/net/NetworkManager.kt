package com.acba.acbadigital.net

import android.content.Context
import android.net.ConnectivityManager
import com.acba.acbadigital.MainActivity
import com.acba.acbadigital.base.AcbaApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> acbaResponse(
    resultCallBack: ApiResultCallBack<T?>,
    showLoader: Boolean,
    function: suspend () -> Response<T>
) {
    if (!isNetworkConnected(AcbaApplication.instance)) {
        resultCallBack.onError("no internet")
        return
    }
    withContext(Dispatchers.IO) {
        if (showLoader) {
            MainActivity.loaderLiveData.postValue(true)
        }
        val response = function.invoke()
        val body = response.body()

        GlobalScope.launch(Dispatchers.Main) {
            if (response.isSuccessful) {
                resultCallBack.onSuccess(body)
            } else {
//                val error = Gson().fromJson(response.errorBody()?.charStream(), BaseResponseModel::class.java)
                resultCallBack.onError("")
            }

            if (showLoader) {
                MainActivity.loaderLiveData.postValue(false)
            }
        }
    }
}


fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo?.isConnected!!
}

interface ApiResultCallBack<T> {

    fun onSuccess(data: T)

    fun onError(status: String?) {}
}

