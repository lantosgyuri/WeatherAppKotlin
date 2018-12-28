package com.lantosgyuri.weatherappkotlin.data.network

import androidx.lifecycle.LiveData

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
            location: String,
            languageCode: String
    )

}