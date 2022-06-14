package com.example.common.base

import android.app.Application
import com.example.common.di.retrofitInstance
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication : Application() {
    abstract val modules: List<Module>
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@BaseApplication)
            modules(retrofitInstance).modules(modules)
        }
    }

    companion object {
        lateinit var instance: BaseApplication
    }
}