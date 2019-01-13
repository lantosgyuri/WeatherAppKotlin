package com.lantosgyuri.weatherappkotlin.data.network

import androidx.lifecycle.LiveData
import com.lantosgyuri.weatherappkotlin.data.network.response.CurrentWeatherResponse
import com.lantosgyuri.weatherappkotlin.data.network.response.FutureWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
            location: String,
            languageCode: String
    )

    suspend fun fethFutureWeather(
            location: String,
            languageCode: String
    )

}