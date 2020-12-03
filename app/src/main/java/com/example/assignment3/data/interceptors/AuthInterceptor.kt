package com.example.assignment3.data.interceptors

import com.example.assignment3.data.SessionManager
import com.example.assignment3.sharedpreferences.SharedPreferencesWrapper
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add auth token to requests
 */
class AuthInterceptor(secureSharedPrefs: SharedPreferencesWrapper) : Interceptor {
    private val sessionManager = SessionManager(secureSharedPrefs)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}