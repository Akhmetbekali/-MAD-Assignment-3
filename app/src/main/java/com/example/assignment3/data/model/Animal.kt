package com.example.assignment3.data.model

import com.google.gson.annotations.SerializedName

data class Animal(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("organization_id")
    val organizationId: String,
    @SerializedName("type")
    val type: String?,
    @SerializedName("breeds")
    val breed: AnimalBreed?,
    @SerializedName("age")
    val age: String,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("photos")
    val photos: Array<AnimalPhoto>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?
)
