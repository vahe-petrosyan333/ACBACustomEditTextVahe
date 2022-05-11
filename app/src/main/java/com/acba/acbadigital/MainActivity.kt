package com.acba.acbadigital

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.acba.acbadigital.databinding.ActivityMainBinding
import com.acba.common.view.validatoredittext.Validable

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

    }

    fun isValid(root: View) {
        if (root is ViewGroup) {
            for (view in root.children) {
                isValid(view)
            }
        } else if (root is Validable) {
            root.validate()
        }

    }
}