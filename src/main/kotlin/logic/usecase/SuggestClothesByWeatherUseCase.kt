package org.example.logic.usecase

import logic.model.WeatherModel
import org.example.logic.model.ClothingType
import org.example.logic.repository.OutfitRepository

class SuggestClothesByWeatherUseCase(
    private val repository: OutfitRepository
) {

    suspend fun suggestClothesByWeather(weatherModel: WeatherModel): List<String> {
        val maxTemp = weatherModel.maxTemp
        val minTemp = weatherModel.minTemp

        val averageTemp = (minTemp + maxTemp) / 2
        val clothingType = getClothingTypeFromAverageTemp(averageTemp)
        return repository.getOutfitForClothingType(clothingType).items
    }

    private fun getClothingTypeFromAverageTemp(avgTemp: Double): ClothingType = when {
        avgTemp < -5 -> ClothingType.HEAVY_WINTER
        avgTemp <= 5 -> ClothingType.WINTER
        avgTemp <= 10 -> ClothingType.MID_SEASON
        avgTemp <= 15 -> ClothingType.LIGHT_JACKET
        avgTemp <= 25 -> ClothingType.CASUAL
        else -> ClothingType.SUMMER
    }
}