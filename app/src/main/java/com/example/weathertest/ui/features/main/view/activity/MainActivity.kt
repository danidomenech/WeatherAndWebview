package com.example.weathertest.ui.features.main.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertest.databinding.ActivityMainBinding
import com.example.weathertest.ui.features.main.view.adapter.PagerAdapter
import com.example.weathertest.ui.features.main.view.fragment.OnFragmentBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var pageAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        pageAdapter = PagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = pageAdapter
        binding.viewPager.setSwipeEnabled(false)

        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onBackPressed() {
        (pageAdapter.getItem(binding.viewPager.currentItem) as? OnFragmentBackPressed)?.onBackFragmentPressed()
    }
}