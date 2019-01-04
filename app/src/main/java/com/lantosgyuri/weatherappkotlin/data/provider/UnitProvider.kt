package com.lantosgyuri.weatherappkotlin.data.provider

import com.lantosgyuri.weatherappkotlin.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}