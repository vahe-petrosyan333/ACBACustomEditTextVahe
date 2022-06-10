package com.example.common.base

import android.app.Application

abstract class BaseApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance:BaseApplication
    }
}