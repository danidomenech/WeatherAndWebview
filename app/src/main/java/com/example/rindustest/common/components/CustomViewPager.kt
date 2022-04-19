package com.example.rindustest.common.components

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context, attrs: AttributeSet): ViewPager(context, attrs) {

    private var swipeEnabled = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return when (swipeEnabled) {
            true -> super.onTouchEvent(ev)
            false -> false
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return when (swipeEnabled) {
            true -> super.onInterceptTouchEvent(ev)
            false -> false
        }
    }

    fun setSwipeEnabled(swipeEnabled: Boolean) {
        this.swipeEnabled = swipeEnabled
    }

}