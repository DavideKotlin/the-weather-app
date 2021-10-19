package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.data.remote.WeatherManager
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.loadWeatherImage
import com.example.weatherapp.viewModels.WeatherViewModel
import com.example.weatherapp.viewModels.WeatherViewModelFactory

class SelectedWeatherFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels() {
        WeatherViewModelFactory(WeatherRepository(WeatherManager()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.selectedWeather?.let { hourlyWeather ->
            with(binding) {
                tvCurrent.text = hourlyWeather.main?.temp?.toInt().toString()
                tvFeelsLike.text = String.format(
                    getString(R.string.feels_like_temp),
                    hourlyWeather.main?.feelsLike?.toInt().toString()
                )
                tvMain.text = hourlyWeather.weather[0].main
                tvDescription.text = hourlyWeather.weather[0].description
                ivWeather.loadWeatherImage(hourlyWeather.weather[0].icon ?: "")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}