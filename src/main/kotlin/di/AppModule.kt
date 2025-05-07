package org.example.di

import org.koin.dsl.module

val appModule = module {
    includes(apiServiceModule, dataModule, useCaseMode, uiModule)
}
