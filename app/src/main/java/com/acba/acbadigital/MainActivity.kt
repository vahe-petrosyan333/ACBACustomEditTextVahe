package com.acba.acbadigital

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.MutableLiveData
import com.acba.acbadigital.databinding.ActivityMainBinding
import com.acba.acbadigital.net.RetrofitInstance
import com.acba.acbadigital.repositories.MainSharedRepositoryImpl
import com.acba.acbadigital.ui.MainViewModel
import com.acba.common.view.validatoredittext.ValidatorListener

class MainActivity : AppCompatActivity() {
    private var mLoading: View? = null
    private val viewModel: MainViewModel =
        MainViewModel(MainSharedRepositoryImpl(RetrofitInstance.getRequestService()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonValidate.setOnClickListener {
                isValid(root)
                Log.d("DOUBLE_CLICK", "click")
            }
        }
        mLoading = findViewById(R.id.loading_layout)
        viewModel.getRates(true)
        viewModel.ratesLiveData.observe(this) {
            print(it)
        }

        loaderLiveData.observe(this) {
            if (it) {
                mLoading?.visibility = View.VISIBLE
            } else {
                mLoading?.visibility = View.GONE
            }
        }
    }

    fun isValid(root: View) {
        if (root is ViewGroup) {
            for (view in root.children) {
                isValid(view)
            }
        } else if (root is ValidatorListener) {
            root.validate()
        }
    }

    companion object {
        val loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    }
}