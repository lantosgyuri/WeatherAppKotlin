package com.lantosgyuri.weatherappkotlin.ui.weather.current

import com.lantosgyuri.weatherappkotlin.data.provider.UnitProvider
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository
import com.lantosgyuri.weatherappkotlin.internal.lazyDeffered
import com.lantosgyuri.weatherappkotlin.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
        private val forecastRepository: ForecastRepository,
        unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeffered{
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }


}
