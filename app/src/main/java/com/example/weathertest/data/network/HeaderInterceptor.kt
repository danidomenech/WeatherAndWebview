package com.example.weathertest.data.network

import com.example.weathertest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val API_KEY_HEADER = "appid"

@Singleton
class HeaderInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val urlBuilder = originalUrl.newBuilder()
        urlBuilder.addQueryParameter(API_KEY_HEADER, BuildConfig.API_KEY)
        val newUrl = urlBuilder.build()

        val builder = originalRequest.newBuilder().url(newUrl)
        return chain.proceed(builder.build())
    }
}