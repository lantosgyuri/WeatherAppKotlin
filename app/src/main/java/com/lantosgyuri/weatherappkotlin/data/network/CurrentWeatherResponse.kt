package com.lantosgyuri.weatherappkotlin.data.network

import com.google.gson.annotations.SerializedName
import com.lantosgyuri.weatherappkotlin.data.db.entity.CurrentWeatherEntry
import com.lantosgyuri.weatherappkotlin.data.db.entity.Location

data class CurrentWeatherResponse(
        @SerializedName("current")
        val currentWeatherEntry: CurrentWeatherEntry,
        val location: Location
)