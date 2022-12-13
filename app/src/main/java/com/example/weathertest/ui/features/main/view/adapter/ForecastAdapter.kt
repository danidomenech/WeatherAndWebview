package com.example.weathertest.ui.features.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weathertest.databinding.ItemForecastBinding
import com.example.weathertest.ui.features.main.model.ForecastWeatherDayUIModel

class ForecastAdapter : ListAdapter<ForecastWeatherDayUIModel, ForecastViewHolder>(ForecastComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            parent.context
        )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }
}