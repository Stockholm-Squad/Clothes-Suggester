package org.example.di

import org.example.data.apiservice.geoLocationLink
import org.example.data.apiservice.ipLocationLink
import org.example.data.apiservice.weatherLink
import org.example.data.datasource.LocationDataSource
import org.example.data.datasource.LocationDataSourceImpl
import org.example.data.datasource.WeatherDataSource
import org.example.data.datasource.WeatherDataSourceImpl
import org.example.data.repository.LocationRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.repository.LocationRepository
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

    factory<LocationRepository> { LocationRepositoryImpl(get()) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }

}