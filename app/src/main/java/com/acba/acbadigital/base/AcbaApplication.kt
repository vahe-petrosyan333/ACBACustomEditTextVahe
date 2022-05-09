package com.acba.acbadigital.base

import android.app.Application

class AcbaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: AcbaApplication
    }
}