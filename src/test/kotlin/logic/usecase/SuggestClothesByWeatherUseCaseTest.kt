package logic.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.model.WeatherModel
import org.example.logic.model.ClothingType
import org.example.logic.repository.OutfitRepository
import org.example.logic.usecase.SuggestClothesByWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SuggestClothesByWeatherUseCaseTest {

    private lateinit var useCase: SuggestClothesByWeatherUseCase
    private lateinit var outfitRepository: OutfitRepository

    @BeforeEach
    fun setUp() {
        outfitRepository = mockk()
        useCase = SuggestClothesByWeatherUseCase(outfitRepository)
    }

    @Test
    fun `returns heavy winter clothes when clothing type is heavy winter`() = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = -6.0,
            minTemp = -10.0,
            maxWindSpeed = 7.8
        )
        val heavyWinterList = listOf(
            "Heavy winter coat", "Thermal layers", "Scarf", "Gloves", "Hat", "Insulated boots"
        )
        coEvery { outfitRepository.getOutfitForClothingType(ClothingType.HEAVY_WINTER).items } returns heavyWinterList

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            heavyWinterList
        )
    }

    @ParameterizedTest
    @CsvSource(
        "-5.0,5.0",
        "-5.0,4.0",
    )
    fun `returns winter clothes for min below -5 and max at most 5`(
        minTemp: Double,
        maxTemp: Double
    ) = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = maxTemp,
            minTemp = minTemp,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "Winter coat", "Sweater or fleece", "Warm pants", "Hat", "Gloves"
        ).inOrder()
    }

    @ParameterizedTest
    @CsvSource(
        "0.0,9.0",
        "5.0,10.0",
        "5.0,9.0",
        "4.0,10.0",
        "4.0,9.0",
    )
    fun `returns mid-weight jacket for min at 0 and max at 9`(
        minTemp: Double,
        maxTemp: Double
    ) = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = maxTemp,
            minTemp = minTemp,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "Mid-weight jacket", "Sweater or hoodie", "Jeans or trousers"
        ).inOrder()
    }

    @Test
    fun `returns light jacket or coat for 10 to 15 degrees`() = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = 15.0,
            minTemp = 10.0,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "Light jacket or coat", "Long-sleeve shirt", "Pants"
        ).inOrder()
    }

    @Test
    fun `returns light sweatshirt and jeans for 15 to 20 degrees`() = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = 20.0,
            minTemp = 15.0,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "Light jacket or sweatshirt", "T-shirt or long-sleeve shirt", "Jeans or light pants"
        ).inOrder()
    }

    @Test
    fun `returns t-shirt and light pants for 20 to 25 degrees`() = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = 25.0,
            minTemp = 20.0,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "T-shirt or polo", "Shorts or light pants", "Optional light sweater in the morning"
        ).inOrder()
    }

    @Test
    fun `returns short sleeves and breathable fabrics for 25 to 30 degrees`() = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = 30.0,
            minTemp = 25.0,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "Short sleeves", "Shorts or skirt", "Breathable fabrics (cotton/linen)"
        ).inOrder()
    }

    @Test
    fun `returns summer clothes for temps above 30`() = runTest {
        //Given
        val weatherModel = WeatherModel(
            date = "",
            maxTemp = 35.0,
            minTemp = 30.0,
            maxWindSpeed = 7.8
        )

        // when
        val result = useCase.suggestClothesByWeather(weatherModel)

        //Then
        assertThat(result).containsExactly(
            "Tank tops", "Shorts", "Sun hat", "Sunglasses", "Sunscreen", "Sandals or light shoes"
        ).inOrder()
    }
}