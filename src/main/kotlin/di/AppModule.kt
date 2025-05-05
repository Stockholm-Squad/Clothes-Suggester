package org.example.di

import apiServiceModule
import org.koin.dsl.module


val appModule = module {
    includes(dataModule, useCaseMode, uiModule, apiServiceModule)
}
