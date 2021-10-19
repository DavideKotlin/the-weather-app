package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.Hourly
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private var _weatherData: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weatherData: LiveData<Resource<WeatherResponse>> get() = _weatherData

    private var _navigateToDetailsScreen: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val navigateToDetailsScreen: LiveData<Boolean> get() = _navigateToDetailsScreen

    var selectedWeather: Hourly? = null

    fun getWeatherForCity(cityName: String) {
        if (cityName.isBlank()) return
        viewModelScope.launch {
            weatherRepository.getWeather(cityName).collect { response ->
                if (response is Resource.Success) _navigateToDetailsScreen.postValue(true)
                _weatherData.postValue(response)
            }
        }
    }
}