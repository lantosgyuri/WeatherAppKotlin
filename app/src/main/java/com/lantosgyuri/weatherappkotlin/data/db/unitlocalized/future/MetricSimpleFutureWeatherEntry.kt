package com.lantosgyuri.weatherappkotlin.data.db.unitlocalized.future

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

class MetricSimpleFutureWeatherEntry(
        @ColumnInfo(name = "date")
        override val date: LocalDate,
        @ColumnInfo(name = "avgTempC")
        override val avgTemperature: Double,
        @ColumnInfo(name = "condition_text")
        override val conditionText: String,
        @ColumnInfo(name = "condition_icon")
        override val conditionUrl: String
) : UnitSpecificSimpleFutureWeatherEntry