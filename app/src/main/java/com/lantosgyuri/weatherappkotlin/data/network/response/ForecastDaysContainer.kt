package com.lantosgyuri.weatherappkotlin.data.network.response

import com.google.gson.annotations.SerializedName
import com.lantosgyuri.weatherappkotlin.data.db.entity.FutureWeatherEntry

data class ForecastDaysContainer(
        @SerializedName("forecastday")
        val entries: List<FutureWeatherEntry>
)