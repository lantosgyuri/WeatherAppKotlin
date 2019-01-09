package com.lantosgyuri.weatherappkotlin.data.provider

import com.lantosgyuri.weatherappkotlin.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}