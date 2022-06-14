package com.example.banking.registration.registrationstepfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banking.R
import com.example.banking.databinding.FragmentPassportOrBankAccountBinding
import com.example.banking.registration.base.BaseRegistrationPageFragment
import com.example.common.view.base.CustomButton


class PassportOrBankAccountFragment : BaseRegistrationPageFragment() {

    private lateinit var binding: FragmentPassportOrBankAccountBinding
    override fun pageTitle(): String {
        return "Registration Type"
    }

    override fun nextButton(): CustomButton {
        return binding.next
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPassportOrBankAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

}