package com.example.banking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.repositories.MainSharedRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainSharedRepository: MainSharedRepository) : ViewModel() {



    fun getRates(showLoader: Boolean) {
        viewModelScope.launch {
//            mainSharedRepository.getRates(apiResultCallBack = object :
//                ApiResultCallBack<RatesResponseModel?> {
//                override fun onSuccess(data: RatesResponseModel?) {
//                    _ratesLiveData.value = data?.rates
//                }
//            }, showLoader)
        }
    }
}