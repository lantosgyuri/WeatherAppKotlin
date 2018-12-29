package com.lantosgyuri.weatherappkotlin.data.repository

import androidx.lifecycle.LiveData
import com.lantosgyuri.weatherappkotlin.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}