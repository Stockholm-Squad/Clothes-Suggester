package org.example.logic.usecase

import logic.model.WeatherModel
import org.example.logic.model.ClothingType

class SuggestClothesByWeatherUseCase {

    fun suggestClothesByWeather(weatherModel: WeatherModel): List<String> {
        val maxTemp = weatherModel.maxTemp
        val minTemp = weatherModel.minTemp

        val averageTemp = (minTemp + maxTemp) / 2
        val clothingType = getClothingTypeFromAverageTemp(averageTemp)
        return when (clothingType) {
            ClothingType.HEAVY_WINTER -> getOutfitForHeavyWinter()
            ClothingType.WINTER -> getOutfitForWinter()
            ClothingType.MID_SEASON -> getOutfitForMidSeason()
            ClothingType.LIGHT_JACKET -> getOutfitForLightJacket()
            ClothingType.CASUAL -> getOutfitForCasual()
            ClothingType.SUMMER -> getOutfitForSummer()
        }
    }

    private fun getClothingTypeFromAverageTemp(avgTemp: Double): ClothingType = when {
        avgTemp < -5 -> ClothingType.HEAVY_WINTER
        avgTemp <= 5 -> ClothingType.WINTER
        avgTemp <= 10 -> ClothingType.MID_SEASON
        avgTemp <= 15 -> ClothingType.LIGHT_JACKET
        avgTemp <= 25 -> ClothingType.CASUAL
        else -> ClothingType.SUMMER
    }

    private fun getOutfitForSummer() = listOf(
        "Tank tops",
        "Shorts",
        "Sun hat",
        "Sunglasses",
        "Sunscreen",
        "Sandals or light shoes"
    )

    private fun getOutfitForCasual() = listOf(
        "T-shirt or polo",
        "Shorts or light pants",
        "Optional light sweater in the morning"
    )

    private fun getOutfitForMidSeason() = listOf(
        "Light jacket or sweatshirt",
        "T-shirt or long-sleeve shirt",
        "Jeans or light pants"
    )

    private fun getOutfitForLightJacket() = listOf(
        "Light jacket or coat",
        "Long-sleeve shirt",
        "Pants"
    )

    private fun getOutfitForWinter() = listOf(
        "Winter coat",
        "Sweater or fleece",
        "Warm pants",
        "Hat",
        "Gloves"
    )

    private fun getOutfitForHeavyWinter() = listOf(
        "Heavy winter coat",
        "Thermal layers",
        "Scarf",
        "Gloves",
        "Hat",
        "Insulated boots"
    )
}