package com.example.banking

import com.example.banking.di.viewModelModule
import com.example.common.base.BaseApplication
import org.koin.core.module.Module

class AcbaApplication: BaseApplication() {
    override val modules: List<Module>
        get() = listOf(repositoryModule, viewModelModule)

}