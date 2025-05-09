package org.example.data.datasource.clothes

import org.example.logic.model.ClothingType
import org.example.logic.model.Outfit

interface OutfitDataSource {
    suspend fun getOutfitForClothingType(type: ClothingType): Outfit
}