package com.example.assignment3.data

import com.example.assignment3.data.request.TokenRequest
import com.example.assignment3.data.response.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST("oauth2/token")
    fun getToken(@Body body: TokenRequest): Call<TokenResponse>

}