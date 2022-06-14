package com.example.banking.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.models.Rates
import com.example.banking.models.RatesResponseModel
import com.example.banking.net.ApiResultCallBack
import com.example.banking.repositories.MainSharedRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainSharedRepository:MainSharedRepository):ViewModel() {

    private val _ratesLiveData:MutableLiveData<Rates?> = MutableLiveData()
    val ratesLiveData:LiveData<Rates?>
    get() = _ratesLiveData

    fun getRates(showLoader: Boolean) {
        viewModelScope.launch {
            mainSharedRepository.getRates(apiResultCallBack = object :
                ApiResultCallBack<RatesResponseModel?> {
                override fun onSuccess(data: RatesResponseModel?) {
                    _ratesLiveData.value = data?.rates
                }
            }, showLoader)
        }
    }
}