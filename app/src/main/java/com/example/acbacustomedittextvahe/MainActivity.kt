package com.example.acbacustomedittextvahe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.acbacommon.ValidatorListener
import com.example.acbacustomedittextvahe.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

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
        } else if (root is ValidatorListener) {
            root.validate()
        }

    }
}