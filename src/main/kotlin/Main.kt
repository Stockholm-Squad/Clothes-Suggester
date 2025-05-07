package org.example

import kotlinx.coroutines.runBlocking
import org.example.di.appModule
import org.example.ui.SuggesterClothesConsole
import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        logger(PrintLogger())
        modules(appModule)
    }

    val suggesterClothesConsole = getKoin().get<SuggesterClothesConsole>()
    runBlocking {
        suggesterClothesConsole.launch()
    }
}