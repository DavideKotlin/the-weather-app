package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.data.models.Hourly
import com.example.weatherapp.data.remote.WeatherManager
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.viewModels.WeatherViewModel
import com.example.weatherapp.viewModels.WeatherViewModelFactory


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherAdapter: WeatherAdapter

    private val weatherViewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory(WeatherRepository(WeatherManager()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        weatherViewModel.weatherData.observe(viewLifecycleOwner, Observer {
            weatherAdapter.differ.submitList(it.data?.list)
        })
    }

    private fun setupRecyclerView() = binding.rvWeatherDays.apply {
        weatherAdapter = WeatherAdapter(this@WeatherFragment::onWeatherClicked)
        adapter = weatherAdapter
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
    }

    private fun onWeatherClicked(weather: Hourly) {
        weatherViewModel.selectedWeather = weather
        findNavController().navigate(R.id.action_weatherFragment_to_detailsFragment)
    }
}

