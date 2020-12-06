package com.example.assignment3.di


import com.example.assignment3.data.AnimalRepository
import com.example.assignment3.data.PetfinderService
import com.example.assignment3.data.db.AnimalBreedDao
import com.example.assignment3.data.db.AnimalDao
import com.example.assignment3.data.db.AnimalTypeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(
        animalDao: AnimalDao,
        animalTypeDao: AnimalTypeDao,
        animalBreedDao: AnimalBreedDao,
        petfinderService: PetfinderService
    ): AnimalRepository {
        return AnimalRepository(animalDao, animalTypeDao, animalBreedDao, petfinderService)
    }
}