package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.remote.WeatherManager
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository(private val weatherManager: WeatherManager) {

    fun getWeather(cityName: String) = flow {
        val resource = try {
            val response = weatherManager.getWeather(cityName)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
            Resource.Error(ex.toString())
        }
        emit(resource)
    }.flowOn(Dispatchers.IO)


    companion object {
        private val TAG = WeatherRepository::class.java.name
    }

}














