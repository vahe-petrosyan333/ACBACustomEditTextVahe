package com.example.banking.registration.base

import com.example.banking.R
import com.example.banking.registration.registrationstepfragment.IndividualOrLegalUserFragment
import com.example.banking.registration.registrationstepfragment.PassportOrBankAccountFragment
import com.example.banking.registration.registrationstepfragment.PassportScanFragment

class RegistrationPagerFragment : BaseRegistrationFragment() {


    override fun toolBarTitle(): String {
        return "Registration"
    }

    override fun toolBarResId(): Int {
        return R.drawable.ic_back
    }


    override fun fragments(): List<BaseRegistrationPageFragment> {
        return arrayListOf(IndividualOrLegalUserFragment(),PassportOrBankAccountFragment(),PassportScanFragment())

    }


}