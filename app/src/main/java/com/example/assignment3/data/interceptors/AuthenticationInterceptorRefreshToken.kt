package com.example.assignment3.data.interceptors

import com.example.assignment3.BuildConfig
import com.example.assignment3.data.SessionManager
import com.example.assignment3.data.TokenService
import com.example.assignment3.data.request.TokenRequest
import com.example.assignment3.sharedpreferences.SharedPreferencesWrapper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptorRefreshToken (
    private val tokenService: TokenService,
    private val secureSharedPrefs: SharedPreferencesWrapper
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val originalRequest = chain.request()
        val authenticationRequest = originalRequest
        val initialResponse = chain.proceed(authenticationRequest)

        when {
            initialResponse.code() == 403 || initialResponse.code() == 401 -> {
                val responseNewTokenLoginModel = tokenService.getToken(
                    TokenRequest(
                        "client_credentials",
                        BuildConfig.CLIENT_ID,
                        BuildConfig.CLIENT_SECRET
                    )
                ).execute()

                return when {
                    responseNewTokenLoginModel == null || responseNewTokenLoginModel.code() != 200 -> {
                        null
                    }
                    else -> {
                        responseNewTokenLoginModel.body()?.token?.let {
                            SessionManager(secureSharedPrefs).saveAuthToken(it)

                        }
                        val newAuthenticationRequest = originalRequest
                            .newBuilder()
                            .addHeader(
                                "Authorization",
                                "Bearer " + responseNewTokenLoginModel.body()?.token)
                            .build()
                        chain.proceed(newAuthenticationRequest)
                    }
                }
            }
            else -> return initialResponse
        }
    }
}