package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appId") apiKey: String = Constants.API_KEY,
        @Query("units") units: String = "imperial"
    ) : Response<WeatherResponse>

}