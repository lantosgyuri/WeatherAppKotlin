package com.lantosgyuri.weatherappkotlin.ui.weather.current

import androidx.lifecycle.ViewModel
import com.lantosgyuri.weatherappkotlin.data.provider.UnitProvider
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository
import com.lantosgyuri.weatherappkotlin.internal.UnitSystem
import com.lantosgyuri.weatherappkotlin.internal.lazyDeffered

class CurrentWeatherViewModel(
        private val forecastRepository: ForecastRepository,
        unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeffered{
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeffered {
        forecastRepository.getWeatherLocation()
    }


}
