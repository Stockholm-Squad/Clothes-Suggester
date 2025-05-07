package org.example.logic.usecase

import logic.model.WeatherModel

class SuggestClothesByWeatherUseCase {

    fun suggestClothesByWeather(weatherModel: WeatherModel): List<String> {
        val maxTemp = weatherModel.maxTemp
        val minTemp = weatherModel.minTemp

        return when {
            maxTemp < -5 -> listOf(
                "Heavy winter coat",
                "Thermal layers",
                "Scarf",
                "Gloves",
                "Hat",
                "Insulated boots"
            )

            minTemp <= -5 && maxTemp <= 5 -> listOf(
                "Winter coat",
                "Sweater or fleece",
                "Warm pants",
                "Hat",
                "Gloves"
            )

            minTemp <= 5 && maxTemp <= 10 -> listOf(
                "Mid-weight jacket",
                "Sweater or hoodie",
                "Jeans or trousers"
            )

            minTemp <= 10 && maxTemp <= 15 -> listOf(
                "Light jacket or coat",
                "Long-sleeve shirt",
                "Pants"
            )

            minTemp <= 15 && maxTemp <= 20 -> listOf(
                "Light jacket or sweatshirt",
                "T-shirt or long-sleeve shirt",
                "Jeans or light pants"
            )

            minTemp <= 20 && maxTemp <= 25 -> listOf(
                "T-shirt or polo",
                "Shorts or light pants",
                "Optional light sweater in the morning"
            )

            minTemp <= 25 && maxTemp <= 30 -> listOf(
                "Short sleeves",
                "Shorts or skirt",
                "Breathable fabrics (cotton/linen)"
            )

            else -> listOf(
                "Tank tops",
                "Shorts",
                "Sun hat",
                "Sunglasses",
                "Sunscreen",
                "Sandals or light shoes"
            )
        }
    }
}