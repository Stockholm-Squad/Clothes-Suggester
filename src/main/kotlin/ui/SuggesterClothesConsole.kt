package org.example.ui

import org.example.logic.usecase.GetLocationUseCase
import org.example.logic.usecase.SuggestClothesByWeatherUseCase

class SuggesterClothesConsole(
    private val locationUseCase: GetLocationUseCase,
    private val weatherUseCase: SuggestClothesByWeatherUseCase,
    private val suggestClothesByWeatherUseCase: SuggestClothesByWeatherUseCase
) {

}