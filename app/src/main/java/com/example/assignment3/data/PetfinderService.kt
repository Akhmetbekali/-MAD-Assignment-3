package com.example.assignment3.data


import com.example.assignment3.data.response.AnimalsResponse
import com.example.assignment3.data.response.BreedsResponse
import com.example.assignment3.data.response.TypesResponse
import io.reactivex.Single
import retrofit2.http.*

interface PetfinderService {

    @GET("animals")
    fun getAnimals(
            @Query ("type") type: String ?= null,
            @Query ("breed") breed: String ?= null
    ): Single<AnimalsResponse>

    @GET("types")
    fun getTypes(): Single<TypesResponse>

    @GET("types/{type}/breeds")
    fun getBreeds(@Path("type") type: String): Single<BreedsResponse>

}
