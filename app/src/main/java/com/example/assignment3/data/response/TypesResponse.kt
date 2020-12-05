package com.example.assignment3.data.response

import com.example.assignment3.data.model.AnimalType
import com.google.gson.annotations.SerializedName

data class TypesResponse(
    @SerializedName("types")
    val typesString: Array<AnimalType>
)