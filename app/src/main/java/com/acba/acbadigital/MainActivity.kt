package com.acba.acbadigital

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.acba.acbadigital.databinding.ActivityMainBinding
import com.acba.acbadigital.models.Rates
import com.acba.acbadigital.repositories.MainRepository
import com.acba.common.view.validatoredittext.ValidatorListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonValidate.setOnClickListener {
                isValid(root)
                Log.d("DOUBLE_CLICK", "click")
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val result: ApiResponse<Rates> = MainRepository.search()
            result.data?.let {
                Log.i("RequestTag", it.toString())
            } ?: run {
                result.exception?.let {
                    withContext(Dispatchers.Main) {
                        it.printStackTrace()
                        Log.i("RequestTag", it.localizedMessage?:"")
                    }
                }
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

}