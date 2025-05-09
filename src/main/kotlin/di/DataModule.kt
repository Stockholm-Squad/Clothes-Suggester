package org.example.di

import org.example.data.apiservice.geoLocationLink
import org.example.data.apiservice.ipLocationLink
import org.example.data.apiservice.weatherLink
import org.example.data.datasource.clothes.OutfitDataSource
import org.example.data.datasource.clothes.OutfitLocalDataSource
import org.example.data.datasource.location.LocationDataSource
import org.example.data.datasource.location.LocationDataSourceImpl
import org.example.data.datasource.weather.WeatherDataSource
import org.example.data.datasource.weather.WeatherDataSourceImpl
import org.example.data.repository.LocationRepositoryImpl
import org.example.data.repository.OutfitRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.repository.LocationRepository
import org.example.logic.repository.OutfitRepository
import org.example.logic.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    factory<LocationDataSource> {
        LocationDataSourceImpl(
            httpClientProvider = get(),
            ipLocationLink = ipLocationLink,
            geoLocationLink = geoLocationLink
        )
    }
    factory<WeatherDataSource> {
        WeatherDataSourceImpl(
            httpClientProvider = get(),
            weatherLink = weatherLink
        )
    }
    factory<OutfitDataSource> { OutfitLocalDataSource() }

    factory<LocationRepository> { LocationRepositoryImpl(get()) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
    factory<OutfitRepository> { OutfitRepositoryImpl(get()) }

}