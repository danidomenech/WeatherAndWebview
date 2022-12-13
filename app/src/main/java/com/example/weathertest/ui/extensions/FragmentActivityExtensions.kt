package com.example.weathertest.ui.extensions

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.weathertest.R

fun FragmentActivity.showCloseDialog() {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(getString(R.string.close_application_title))
    builder.setMessage(getString(R.string.close_application_message))
    builder.setPositiveButton(getString(R.string.close_yes)) { dialog, _ ->
        finish()
    }
    builder.setNegativeButton(getString(R.string.close_no)) { dialog, _ ->

    }
    builder.show()
}