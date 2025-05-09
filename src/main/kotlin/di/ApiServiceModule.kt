package org.example.di

import org.example.data.apiservice.HttpClientProvider
import org.example.data.apiservice.HttpClientProviderImp
import org.koin.dsl.module

val apiServiceModule = module {

    single<HttpClientProvider> { HttpClientProviderImp() }
}