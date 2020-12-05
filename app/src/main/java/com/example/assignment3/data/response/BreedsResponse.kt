package com.example.assignment3.data.response

import com.example.assignment3.data.model.AnimalBreed
import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("breeds")
    val breedsString: Array<AnimalBreed>
)