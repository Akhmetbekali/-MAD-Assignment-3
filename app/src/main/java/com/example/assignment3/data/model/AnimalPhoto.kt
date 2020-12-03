package com.example.assignment3.data.model


import com.google.gson.annotations.SerializedName

data class AnimalPhoto(
    @SerializedName("small")
    val photoSmall: String,

    @SerializedName("full")
    val photoFull: String
)