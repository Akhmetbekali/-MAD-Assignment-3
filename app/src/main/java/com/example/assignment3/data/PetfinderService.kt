package com.example.assignment3.data


import com.example.assignment3.data.response.AnimalsResponse
import io.reactivex.Single
import retrofit2.http.*

interface PetfinderService {

    @GET("animals")
    fun getAnimals(): Single<AnimalsResponse>

}
