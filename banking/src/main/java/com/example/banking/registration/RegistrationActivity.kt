package com.example.banking.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.banking.R
import com.example.banking.registration.base.BaseRegistrationFragment

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.get(0) is BaseRegistrationFragment){
            (supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.get(0) as BaseRegistrationFragment).popBack()
        }
    }
}