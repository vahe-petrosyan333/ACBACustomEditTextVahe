package com.example.acbacustomedittextvahe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.acbacustomedittextvahe.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonValidate.setOnClickListener {
                if (!email.validate()) {
                 val  inputLayout :TextInputLayout =  findViewById<TextInputLayout>(email.layoutId)
                    inputLayout.error = "incorect email"
//                    Toast.makeText(this@MainActivity, "Valid", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}