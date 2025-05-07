package org.example.logic.usecase

import logic.model.WeatherModel

class SuggestClothesByWeatherUseCase {

    fun suggestClothesByWeather(weatherModel: WeatherModel): List<String> {
        val maxTemp = weatherModel.maxTemp
        val minTemp = weatherModel.minTemp

        return when {
            maxTemp < -5 -> getOutfitForTempLessThanNegativeFive()
            minTemp <= -5 && maxTemp <= 5 -> getOutfitForTempLessThanOrEqualFive()
            minTemp <= 5 && maxTemp <= 10 -> getOutfitForTempLessThanOrEqualTen()
            minTemp <= 10 && maxTemp <= 15 -> getOutfitForTempLessThanOrEqualFifteen()
            minTemp <= 15 && maxTemp <= 20 -> getOutfitForTempLessThanOrEqualTwenty()
            minTemp <= 20 && maxTemp <= 25 -> getOutfitForTempLessThanOrEqualTwentyFive()
            minTemp <= 25 && maxTemp <= 30 -> getOutfitForTempLessThanOrEqualThirty()
            else -> getOutfitForTempMoreThanThirty()
        }
    }

    private fun getOutfitForTempMoreThanThirty() = listOf(
        "Tank tops",
        "Shorts",
        "Sun hat",
        "Sunglasses",
        "Sunscreen",
        "Sandals or light shoes"
    )

    private fun getOutfitForTempLessThanOrEqualThirty() = listOf(
        "Short sleeves",
        "Shorts or skirt",
        "Breathable fabrics (cotton/linen)"
    )

    private fun getOutfitForTempLessThanOrEqualTwentyFive() = listOf(
        "T-shirt or polo",
        "Shorts or light pants",
        "Optional light sweater in the morning"
    )

    private fun getOutfitForTempLessThanOrEqualTwenty() = listOf(
        "Light jacket or sweatshirt",
        "T-shirt or long-sleeve shirt",
        "Jeans or light pants"
    )

    private fun getOutfitForTempLessThanOrEqualFifteen() = listOf(
        "Light jacket or coat",
        "Long-sleeve shirt",
        "Pants"
    )

    private fun getOutfitForTempLessThanOrEqualTen() = listOf(
        "Mid-weight jacket",
        "Sweater or hoodie",
        "Jeans or trousers"
    )

    private fun getOutfitForTempLessThanOrEqualFive() = listOf(
        "Winter coat",
        "Sweater or fleece",
        "Warm pants",
        "Hat",
        "Gloves"
    )

    private fun getOutfitForTempLessThanNegativeFive() = listOf(
        "Heavy winter coat",
        "Thermal layers",
        "Scarf",
        "Gloves",
        "Hat",
        "Insulated boots"
    )
}