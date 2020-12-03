package com.example.assignment3.data.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val token: String
)