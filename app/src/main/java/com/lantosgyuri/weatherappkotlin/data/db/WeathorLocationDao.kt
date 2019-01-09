package com.lantosgyuri.weatherappkotlin.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lantosgyuri.weatherappkotlin.data.db.entity.WEATHER_LOCATION_ID
import com.lantosgyuri.weatherappkotlin.data.db.entity.WeatherLocation

@Dao
interface WeathorLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: WeatherLocation)

    @Query("select * from weather_location where id = $WEATHER_LOCATION_ID")
    fun getLocation(): LiveData<WeatherLocation>
}