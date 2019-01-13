package com.lantosgyuri.weatherappkotlin.ui.weather.future.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lantosgyuri.weatherappkotlin.data.provider.UnitProvider
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository

class FutureLitWeatherViewModelFatory(
        private val forecastRepository: ForecastRepository,
        private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureLsitWeatherViewModel(
                forecastRepository,
                unitProvider
        ) as T
    }
}