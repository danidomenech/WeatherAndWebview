package com.example.rindustest.ui.features.main.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rindustest.ui.features.main.model.ForecastWeatherDayUIModel

object ForecastComparator : DiffUtil.ItemCallback<ForecastWeatherDayUIModel>() {

    override fun areItemsTheSame(oldItem: ForecastWeatherDayUIModel, newItem: ForecastWeatherDayUIModel) =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: ForecastWeatherDayUIModel, newItem: ForecastWeatherDayUIModel) =
        oldItem == newItem

}