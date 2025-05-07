package org.example.di

import org.example.data.datasource.LocationDataSource
import org.example.data.datasource.LocationDataSourceImpl
import org.example.data.datasource.WeatherDataSource
import org.example.data.datasource.WeatherDataSourceImpl
import org.example.data.repository.LocationRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.repository.LocationRepository
import org.example.logic.repository.WeatherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    factory<LocationDataSource> {
        LocationDataSourceImpl(
            ipApiClient = get(named("ipApiClient")),
            geoApiClient = get(named("geoApiClient"))
        )
    }
    factory<WeatherDataSource> { WeatherDataSourceImpl(get (named("weatherClient"))) }

    factory<LocationRepository> { LocationRepositoryImpl(get()) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }

}