package com.example.rindustest.ui.features.main.view.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.rindustest.R
import com.example.rindustest.databinding.ItemForecastBinding
import com.example.rindustest.ui.extensions.toStringFormatted
import com.example.rindustest.ui.features.main.model.ForecastWeatherDayUIModel

class ForecastViewHolder(private val binding: ItemForecastBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ForecastWeatherDayUIModel) {
        binding.itemForecastDate.text = item.date.toStringFormatted("EEE MMM dd")
        binding.itemForecastTemperature.text = context.getString(R.string.forecast_max_min_temperature, item.maxTemperature.toString(), item.minTemperature.toString())
        binding.itemForecastDescription.text = item.description
    }

}