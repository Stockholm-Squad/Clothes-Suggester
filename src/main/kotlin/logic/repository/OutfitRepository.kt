package org.example.logic.repository

import org.example.logic.model.ClothingType
import org.example.logic.model.Outfit

interface OutfitRepository {
    suspend fun getOutfitForClothingType(type: ClothingType): Outfit
}