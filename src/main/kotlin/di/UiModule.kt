package org.example.di

import org.example.ui.SuggesterClothesConsole
import org.koin.dsl.module

val uiModule = module {
    single { SuggesterClothesConsole(
        get(), get(), get()
    ) }
}
