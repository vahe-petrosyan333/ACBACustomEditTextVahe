package com.example.banking.net

import retrofit2.Retrofit

class ServiceFactory(private val retrofit: Retrofit) {
    fun provideDatSource() = retrofit.create(DataSource::class.java)
}