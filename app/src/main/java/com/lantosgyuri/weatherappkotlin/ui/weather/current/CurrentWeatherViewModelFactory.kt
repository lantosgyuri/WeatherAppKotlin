package com.lantosgyuri.weatherappkotlin.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lantosgyuri.weatherappkotlin.data.provider.UnitProvider
import com.lantosgyuri.weatherappkotlin.data.repository.ForecastRepository

class CurrentWeatherViewModelFactory(
        private val forecastRepository: ForecastRepository,
        private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository, unitProvider) as T
    }
}