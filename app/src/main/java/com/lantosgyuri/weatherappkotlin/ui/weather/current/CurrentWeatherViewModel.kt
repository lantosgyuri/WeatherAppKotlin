package com.lantosgyuri.weatherappkotlin.ui.weather.current

import androidx.lifecycle.ViewModel
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository
import com.lantosgyuri.weatherappkotlin.internal.UnitSystem
import com.lantosgyuri.weatherappkotlin.internal.lazyDeffered

class CurrentWeatherViewModel(
        private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC
    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeffered{
        forecastRepository.getCurrentWeather(isMetric)
    }
}
