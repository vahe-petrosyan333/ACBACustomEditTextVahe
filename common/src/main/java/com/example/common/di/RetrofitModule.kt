package com.example.common.di

import com.example.common.base.net.provideRetrofit
import org.koin.dsl.module

val retrofitInstance = module {
    single {
        provideRetrofit()
    }
}