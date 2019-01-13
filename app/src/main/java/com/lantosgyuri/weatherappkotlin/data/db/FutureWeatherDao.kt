package com.lantosgyuri.weatherappkotlin.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lantosgyuri.weatherappkotlin.data.db.entity.FutureWeatherEntry
import com.lantosgyuri.weatherappkotlin.data.db.unitlocalized.future.ImperialSimpleFutureWeatheEnrty
import com.lantosgyuri.weatherappkotlin.data.db.unitlocalized.future.MetricSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries: List<FutureWeatherEntry>)

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastsMetric(startDate: LocalDate): LiveData<List<MetricSimpleFutureWeatherEntry>>

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastsImperial(startDate: LocalDate): LiveData<List<ImperialSimpleFutureWeatheEnrty>>

    @Query("select count(id) from future_weather where data(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query("delete from future_weather where data(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)
}