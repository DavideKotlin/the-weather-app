package com.example.weatherapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWeatherImage(imagePath: String) {
    Glide.with(context).load("https://openweathermap.org/img/w/$imagePath.png").centerCrop()
        .into(this)
}