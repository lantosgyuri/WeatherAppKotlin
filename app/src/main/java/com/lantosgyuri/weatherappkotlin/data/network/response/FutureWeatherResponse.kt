package com.lantosgyuri.weatherappkotlin.data.network.response

import com.google.gson.annotations.SerializedName
import com.lantosgyuri.weatherappkotlin.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
        @SerializedName("forecast")
        val futureWeatherEntries: ForecastDaysContainer,
        val location: WeatherLocation
)