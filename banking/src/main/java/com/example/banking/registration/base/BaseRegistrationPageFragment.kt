package com.example.banking.registration.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.common.view.base.CustomButton


abstract class BaseRegistrationPageFragment : Fragment() {

    abstract fun pageTitle(): String
    abstract fun nextButton(): CustomButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireParentFragment() is BaseRegistrationFragment) {
            nextButton().setOnClickListener(requireParentFragment() as BaseRegistrationFragment)
        }
    }

}
