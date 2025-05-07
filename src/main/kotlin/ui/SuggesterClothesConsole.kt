package org.example.ui

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.example.logic.usecase.GetLocationUseCase
import org.example.logic.usecase.GetWeatherUseCase
import org.example.logic.usecase.SuggestClothesByWeatherUseCase

class SuggesterClothesConsole(
    private val getLocationUseCase: GetLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val suggestClothesByWeatherUseCase: SuggestClothesByWeatherUseCase
) {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception: ${throwable.message}")
        println("Please try again ^_^")
    }

    suspend fun launch() = coroutineScope {
        while (true) {
            showMenu()
            val choice = readlnOrNull()
            when (choice) {
                "1" -> suggestClothesByCurrentLocation()
                "2" -> suggestClothesByCountryAndCity()
                "3" -> {
                    println("Goodbye!")
                    break
                }

                else -> showInputFailure()
            }
        }
    }

    private fun showInputFailure() {
        println("Invalid choice, please try again ^_^")
    }

    private fun showMenu() {
        println("------------------------------------------------")
        println("-----Welcome to Clothes suggester App-----")
        println("Please select what do you want ^_^")
        println("1. Show suitable clothes based on your current location")
        println("2. Show suitable clothes based on specific country and city")
        println("3. Exit")
        println("------------------------------------------------")
    }

    private suspend fun suggestClothesByCurrentLocation() = withContext(Dispatchers.IO + exceptionHandler) {
        try {
            val location = getLocationUseCase.getCurrentLocation()
            if (location != null) {
                val weather = getWeatherUseCase.getWeather(location)
                if (weather != null) {
                    showSuitableClothes(
                        weather.minTemp,
                        weather.maxTemp,
                        suggestClothesByWeatherUseCase.suggestClothesByWeather(weather)
                    )
                } else {
                    println("Error while getting weather, Please try again ^_^")
                }
            } else {
                println("Error while getting location, Please try again ^_^")
            }
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
        }
    }

    private suspend fun suggestClothesByCountryAndCity() = withContext(Dispatchers.IO + exceptionHandler) {
        try {
            print("Please enter a country: ")
            val country = readlnOrNull()
            print("Please enter a city: ")
            val city = readlnOrNull()

            val location = getLocationUseCase.getLocationByCountryAndCity(country, city)
            if (location != null) {
                val weather = getWeatherUseCase.getWeather(location)
                if (weather != null) {
                    showSuitableClothes(
                        weather.minTemp,
                        weather.maxTemp,
                        suggestClothesByWeatherUseCase.suggestClothesByWeather(weather)
                    )
                } else {
                    println("Error while getting weather, Please try again ^_^")
                }
            } else {
                println("Error while getting location, Please try again ^_^")
            }
        } catch (e: Exception) {
            println("Unexpected error: ${e.message}")
        }
    }

    private fun showSuitableClothes(minTemp: Double, maxTemp: Double, suggestions: List<String>) {
        println("Clothing suggestions for min: $minTemp°C, max: $maxTemp°C")
        suggestions.forEach { println("- $it") }
    }
}