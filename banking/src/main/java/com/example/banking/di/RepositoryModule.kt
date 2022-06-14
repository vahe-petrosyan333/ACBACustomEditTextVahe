package com.example.banking

import com.example.banking.net.ServiceFactory
import com.example.banking.repositories.MainSharedRepository
import com.example.banking.repositories.MainSharedRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { ServiceFactory(get()).provideDatSource() }
    factory<MainSharedRepository> { MainSharedRepositoryImpl(get()) }
}