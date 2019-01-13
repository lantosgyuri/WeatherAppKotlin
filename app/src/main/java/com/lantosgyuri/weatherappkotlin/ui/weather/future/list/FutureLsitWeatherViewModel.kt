package com.lantosgyuri.weatherappkotlin.ui.weather.future.list

import com.lantosgyuri.weatherappkotlin.data.provider.UnitProvider
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository
import com.lantosgyuri.weatherappkotlin.internal.lazyDeffered
import com.lantosgyuri.weatherappkotlin.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureLsitWeatherViewModel(
        private val forecastRepository: ForecastRepository,
        private val unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeffered {
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
    }
}
