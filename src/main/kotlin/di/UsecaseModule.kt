package org.example.di

import org.example.logic.usecase.GetLocationUseCase
import org.example.logic.usecase.GetWeatherUseCase
import org.example.logic.usecase.SuggestClothesByWeatherUseCase
import org.koin.dsl.module

val useCaseMode = module {
    factory { GetLocationUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
    factory { SuggestClothesByWeatherUseCase(get()) }
}
