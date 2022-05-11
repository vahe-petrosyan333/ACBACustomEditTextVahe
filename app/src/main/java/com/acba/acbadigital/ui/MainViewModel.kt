package com.acba.acbadigital.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acba.acbadigital.models.Rates
import com.acba.acbadigital.models.RatesResponseModel
import com.acba.acbadigital.net.ApiResultCallBack
import com.acba.acbadigital.repositories.MainSharedRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainSharedRepository: MainSharedRepository) : ViewModel() {

    private val _ratesLiveData: MutableLiveData<Rates?> = MutableLiveData()
    val ratesLiveData: LiveData<Rates?>
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