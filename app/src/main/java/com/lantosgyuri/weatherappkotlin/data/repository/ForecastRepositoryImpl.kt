package com.lantosgyuri.weatherappkotlin.data.repository

import androidx.lifecycle.LiveData
import com.lantosgyuri.weatherappkotlin.data.db.CurrentWeatherDao
import com.lantosgyuri.weatherappkotlin.data.db.FutureWeatherDao
import com.lantosgyuri.weatherappkotlin.data.db.WeathorLocationDao
import com.lantosgyuri.weatherappkotlin.data.db.entity.WeatherLocation
import com.lantosgyuri.weatherappkotlin.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.lantosgyuri.weatherappkotlin.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import com.lantosgyuri.weatherappkotlin.data.network.FORECAST_DAYS_COUNT
import com.lantosgyuri.weatherappkotlin.data.network.WeatherNetworkDataSource
import com.lantosgyuri.weatherappkotlin.data.network.response.CurrentWeatherResponse
import com.lantosgyuri.weatherappkotlin.data.network.response.FutureWeatherResponse
import com.lantosgyuri.weatherappkotlin.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val futureWeatherDao: FutureWeatherDao,
        private val weatherLocationDao: WeathorLocationDao,
        private val weatherNetworkDataSource: WeatherNetworkDataSource,
        private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.apply {
            downloadCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }
            downloadFutureWeather.observeForever { newFutureWeather ->
                persistFetchedFutureWeather(newFutureWeather)
            }

        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

    override suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
        return withContext((Dispatchers.IO)) {
            initWeatherData()
            return@withContext if (metric) futureWeatherDao.getSimpleWeatherForecastsMetric(startDate)
            else futureWeatherDao.getSimpleWeatherForecastsImperial(startDate)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private fun persistFetchedFutureWeather(fetchedWeather: FutureWeatherResponse) {
        fun deleteOldForecestData() {
            val today = LocalDate.now()
            futureWeatherDao.deleteOldEntries(today)
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForecestData()
            val futureWeatherList = fetchedWeather.futureWeatherEntries.entries
            futureWeatherDao.insert(futureWeatherList)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private suspend fun initWeatherData(){
        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()

        if (lastWeatherLocation == null
                || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            fetchFutureWeather()
            return
        }

        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather()

        if (isFetchFutureNeeded())
            fetchFutureWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
                locationProvider.getPreferredLocationString(),
                Locale.getDefault().language)
    }

    private suspend fun fetchFutureWeather() {
        weatherNetworkDataSource.fethFutureWeather(
                locationProvider.getPreferredLocationString(),
                Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private fun isFetchFutureNeeded(): Boolean {
        val today = LocalDate.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount < FORECAST_DAYS_COUNT
    }
}