package com.example.rindustest.ui.features.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rindustest.R
import com.example.rindustest.databinding.FragmentWeatherBinding
import com.example.rindustest.ui.extensions.hide
import com.example.rindustest.ui.extensions.show
import com.example.rindustest.ui.extensions.showCloseDialog
import com.example.rindustest.ui.extensions.toStringFormatted
import com.example.rindustest.ui.features.main.model.CurrentWeatherUIModel
import com.example.rindustest.ui.features.main.model.ForecastList
import com.example.rindustest.ui.features.main.view.adapter.ForecastAdapter
import com.example.rindustest.ui.features.main.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val DUSSELDORF_NAME = "Dusseldorf"
private const val FORECAST_DAYS = 5

@AndroidEntryPoint
class WeatherFragment : Fragment(), OnFragmentBackPressed {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private val forecastAdapter : ForecastAdapter by lazy { ForecastAdapter() }

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }

    private fun initBack() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().showCloseDialog()
                }
            })
    }

    private fun initRecyclerView() {
        binding.forecastRecyclerView.apply {
            adapter = forecastAdapter
        }
    }

    private fun loadForecast() {
        weatherViewModel.loadCurrentWeatherAndForecastFromCity(DUSSELDORF_NAME, FORECAST_DAYS)
    }

    private fun observeViewModel() {
        weatherViewModel.currentWeather.observe(this, { currentWeatherUIModel ->
            fillCurrentWeahterData(currentWeatherUIModel)
        })
        weatherViewModel.forecast.observe(this, { forecastList ->
            fillForecastData(forecastList)
        })
        weatherViewModel.loading.observe(this, { loading ->
            showProgressBar(loading)
        })
        weatherViewModel.error.observe(this, { message ->
            showErrorDialog(message)
        })
    }

    private fun fillCurrentWeahterData(currentWeatherDayUIModel: CurrentWeatherUIModel) {
        binding.currentWeatherVerticalSeparator.visibility = View.VISIBLE
        binding.currentWeatherDateText.text = currentWeatherDayUIModel.weather?.date?.toStringFormatted()
        binding.currentWeatherCityText.text = currentWeatherDayUIModel.city + ", " + currentWeatherDayUIModel.country
        binding.currentWeatherTemperatureText.text = getString(R.string.current_temperature, currentWeatherDayUIModel.weather?.temperature.toString())
        binding.currentWeatherTemperatureMinText.text = getString(R.string.current_min_temperature, currentWeatherDayUIModel.weather?.minTemperature.toString())
        binding.currentWeatherTemperatureMaxText.text = getString(R.string.current_max_temperature, currentWeatherDayUIModel.weather?.maxTemperature.toString())
        binding.currentWeatherDescriptionText.text = getString(R.string.current_weather_description, currentWeatherDayUIModel.weather?.feelsLikeTemperature.toString(), currentWeatherDayUIModel.weather?.description?.capitalize())
        binding.currentWeatherWindText.text = getString(R.string.current_weather_wind, currentWeatherDayUIModel.weather?.windSpeed.toString())
        binding.currentWeatherHumidityText.text = getString(R.string.current_weather_humidity, currentWeatherDayUIModel.weather?.humidity.toString())
        binding.currentWeatherVisibilityText.text = getString(R.string.current_weather_visibility, currentWeatherDayUIModel.weather?.visibilityKilometers.toString())
        binding.currentWeatherPressureText.text = getString(R.string.current_weather_pressure, currentWeatherDayUIModel.weather?.pressure.toString())
        binding.currentWeatherCloudinessText.text = getString(R.string.current_weather_cloudiness, currentWeatherDayUIModel.weather?.cloudiness.toString())
        binding.forecastTitle.text = getString(R.string.forecast_title, FORECAST_DAYS.toString())
    }

    private fun fillForecastData(forecastList: ForecastList) {
        forecastAdapter.submitList(forecastList.weatherDays)
        binding.separator.visibility = View.VISIBLE
    }

    private fun showProgressBar(visible : Boolean) {
        if(visible) binding.progress.show() else binding.progress.hide()
    }

    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(getString(R.string.error_dialog_title))
        builder.setMessage(message)
        builder.setPositiveButton(getString(R.string.button_retry)) { dialog, _ ->
            loadForecast()
        }
        builder.show()
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
        initBack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackFragmentPressed() {
        requireActivity().showCloseDialog()
    }
}