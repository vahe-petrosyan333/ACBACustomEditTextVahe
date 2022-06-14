package com.example.banking.registration.registrationstepfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banking.databinding.FragmentPassportScanBinding
import com.example.banking.registration.base.BaseRegistrationPageFragment
import com.example.common.view.base.CustomButton


class PassportScanFragment : BaseRegistrationPageFragment() {
    override fun pageTitle(): String {
        return "Document Scan"
    }

    private lateinit var binding: FragmentPassportScanBinding
    override fun nextButton(): CustomButton {
        return binding.next
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPassportScanBinding.inflate(inflater, container, false)
        return binding.root
    }


}