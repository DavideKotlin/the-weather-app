package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Hourly
import com.example.weatherapp.databinding.ItemWeatherBinding

class WeatherAdapter(
    private val listener: (weather: Hourly) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<Hourly>() {
        override fun areItemsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
            return oldItem.wind == newItem.wind
        }

        override fun areContentsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


    class WeatherViewHolder(
        private val binding: ItemWeatherBinding,
        private val listener: (weather: Hourly) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherItem: Hourly) {
            with(binding) {
                root.setOnClickListener {
                    listener.invoke(weatherItem)
                }
                tvDays.text = weatherItem.weather[0].main
                tvDayTemp.text = weatherItem.main?.temp?.toInt().toString()
            }
        }
    }
}
