package org.example.di

import apiServiceModule
import org.koin.dsl.module


val appModule = module {
    includes(apiServiceModule, dataModule, useCaseMode, uiModule)
}
