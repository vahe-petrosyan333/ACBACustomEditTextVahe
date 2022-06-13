package com.example.banking.registration.registrationstepfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banking.databinding.FragmentIndividualOrLegalUserBinding
import com.example.banking.registration.base.BaseRegistrationPageFragment
import com.example.common.view.base.CustomButton

class IndividualOrLegalUserFragment : BaseRegistrationPageFragment() {

    private lateinit var binding: FragmentIndividualOrLegalUserBinding

    override fun pageTitle(): String {
        return "Registration user type"
    }

    override fun nextButton(): CustomButton {
        return binding.next
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndividualOrLegalUserBinding.inflate(inflater, container, false)
        return binding.root
    }


}