package com.lantosgyuri.weatherappkotlin.ui.base

import androidx.lifecycle.ViewModel
import com.lantosgyuri.weatherappkotlin.data.provider.UnitProvider
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository
import com.lantosgyuri.weatherappkotlin.internal.UnitSystem
import com.lantosgyuri.weatherappkotlin.internal.lazyDeffered

abstract class WeatherViewModel(
        private val forecastRepository: ForecastRepository,
        private val unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeffered {
        forecastRepository.getWeatherLocation()
    }
}