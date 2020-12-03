package com.example.assignment3.data

import com.example.assignment3.sharedpreferences.SharedPreferencesWrapper


class SessionManager(private val secureSharedPrefs: SharedPreferencesWrapper) {

    companion object {
        const val AUTH_TOKEN = "auth_token"
    }

    fun saveAuthToken(token: String) {
        secureSharedPrefs.set(AUTH_TOKEN, token)
    }

    fun fetchAuthToken(): String? {
        return secureSharedPrefs.getString(AUTH_TOKEN)
    }
}