package com.example.weathertest.ui.features.main.view.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.weathertest.databinding.FragmentWebviewBinding
import com.example.weathertest.ui.extensions.hide
import com.example.weathertest.ui.extensions.show
import com.example.weathertest.ui.extensions.showCloseDialog
import dagger.hilt.android.AndroidEntryPoint

private const val C_AND_A_WEB_URL = "https://www.c-and-a.com"

@AndroidEntryPoint
class WebviewFragment : Fragment(), OnFragmentBackPressed {

    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!

    private var loadingFinished = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebview()
    }

    private fun setUpWebview() {
        val webview = binding.fragmentWebviewWebview
        webview.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadingFinished = false
                showProgressBar(true)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                loadingFinished = true
                showProgressBar(false)
            }
        }
        webview.loadUrl(C_AND_A_WEB_URL)
        webview.settings.javaScriptEnabled = true

    }

    private fun showProgressBar(visible : Boolean) {
        if(visible) binding.progress.show() else binding.progress.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackFragmentPressed() {
        if(binding.fragmentWebviewWebview.canGoBack()) {
            binding.fragmentWebviewWebview.goBack()
        }
        else {
            requireActivity().showCloseDialog()
        }
    }
}