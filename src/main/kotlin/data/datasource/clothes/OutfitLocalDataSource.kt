package org.example.data.datasource.clothes

import org.example.logic.model.ClothingType
import org.example.logic.model.Outfit

class OutfitLocalDataSource : OutfitDataSource {

    override suspend fun getOutfitForClothingType(type: ClothingType): Outfit {
        return when (type) {
            ClothingType.HEAVY_WINTER -> Outfit(type, getOutfitForHeavyWinter())
            ClothingType.WINTER -> Outfit(type, getOutfitForWinter())
            ClothingType.MID_SEASON -> Outfit(type, getOutfitForMidSeason())
            ClothingType.LIGHT_JACKET -> Outfit(type, getOutfitForLightJacket())
            ClothingType.CASUAL -> Outfit(type, getOutfitForCasual())
            ClothingType.SUMMER -> Outfit(type, getOutfitForSummer())
        }
    }

    private fun getOutfitForSummer() = listOf(
        "Tank tops", "Shorts", "Sun hat", "Sunglasses", "Sunscreen", "Sandals or light shoes"
    )

    private fun getOutfitForCasual() = listOf(
        "T-shirt or polo", "Shorts or light pants", "Optional light sweater in the morning"
    )

    private fun getOutfitForMidSeason() = listOf(
        "Light jacket or sweatshirt", "T-shirt or long-sleeve shirt", "Jeans or light pants"
    )

    private fun getOutfitForLightJacket() = listOf(
        "Light jacket or coat", "Long-sleeve shirt", "Pants"
    )

    private fun getOutfitForWinter() = listOf(
        "Winter coat", "Sweater or fleece", "Warm pants", "Hat", "Gloves"
    )

    private fun getOutfitForHeavyWinter() = listOf(
        "Heavy winter coat", "Thermal layers", "Scarf", "Gloves", "Hat", "Insulated boots"
    )
}