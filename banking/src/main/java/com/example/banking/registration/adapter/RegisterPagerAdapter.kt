package com.example.banking.registration.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.banking.registration.base.BaseRegistrationPageFragment

class RegisterPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragmentsList: List<BaseRegistrationPageFragment>,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager,lifecycle) {


    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }

}