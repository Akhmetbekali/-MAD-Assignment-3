package com.example.assignment3.data.response


import com.example.assignment3.data.model.Animal
import com.google.gson.annotations.SerializedName

data class AnimalsResponse(
    @SerializedName("animals")
    val animalsArray: Array<Animal>
)