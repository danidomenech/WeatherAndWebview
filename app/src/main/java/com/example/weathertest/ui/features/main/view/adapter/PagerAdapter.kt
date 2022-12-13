package com.example.weathertest.ui.features.main.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weathertest.R
import com.example.weathertest.ui.features.main.view.fragment.WeatherFragment
import com.example.weathertest.ui.features.main.view.fragment.WebviewFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class PagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val weatherFragment: WeatherFragment by lazy { WeatherFragment() }
    private val webviewFragmentFragment: WebviewFragment by lazy { WebviewFragment() }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            1 -> webviewFragmentFragment
            else -> weatherFragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}