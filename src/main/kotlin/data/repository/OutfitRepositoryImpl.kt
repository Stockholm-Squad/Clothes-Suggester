package org.example.data.repository

import org.example.data.datasource.clothes.OutfitDataSource
import org.example.logic.model.ClothingType
import org.example.logic.model.Outfit
import org.example.logic.repository.OutfitRepository

class OutfitRepositoryImpl(
    private val outfitDataSource: OutfitDataSource
) : OutfitRepository {
    override suspend fun getOutfitForClothingType(type: ClothingType): Outfit {
        return outfitDataSource.getOutfitForClothingType(type)
    }
}