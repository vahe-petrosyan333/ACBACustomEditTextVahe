package com.example.banking.registration.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.banking.databinding.FragmentBaseRegistrationBinding
import com.example.banking.registration.adapter.RegisterPagerAdapter

abstract class BaseRegistrationFragment : Fragment(), View.OnClickListener {

    protected lateinit var binding: FragmentBaseRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseRegistrationBinding.inflate(inflater, container, false)
        setViewPagerAdapter()
        setUpToolBar()
        onRegisterPageSelected()
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUpToolBar() {
        binding.registrationToolBar.title = toolBarTitle()
        binding.registrationToolBar.navigationIcon = context?.getDrawable(toolBarResId())
        binding.registrationToolBar.setNavigationOnClickListener {
            popBack()
        }
    }

    private fun setViewPagerAdapter() {
        binding.viewPager.adapter =
            RegisterPagerAdapter(childFragmentManager, fragments(), lifecycle)
    }

    private fun onRegisterPageSelected() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.registrationToolBar.title = fragments()[position].pageTitle()
            }
        })
    }

    override fun onClick(p0: View?) {
        if (binding.viewPager.currentItem < fragments().size - 1) {
            binding.viewPager.currentItem++
        } else {
            //TODO register
        }
    }

     fun popBack() {
        if (binding.viewPager.currentItem > 0) {
            binding.viewPager.currentItem--
        } else {
            requireActivity().onBackPressed()
        }
    }

    abstract fun toolBarTitle(): String
    abstract fun toolBarResId(): Int
    abstract fun fragments(): List<BaseRegistrationPageFragment>
}