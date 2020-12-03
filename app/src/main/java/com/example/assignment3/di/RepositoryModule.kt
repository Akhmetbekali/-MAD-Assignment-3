package com.example.assignment3.di


import com.example.assignment3.data.AnimalRepository
import com.example.assignment3.data.PetfinderService
import com.example.assignment3.data.db.AnimalDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(
        animalDao: AnimalDao,
        petfinderService: PetfinderService
    ): AnimalRepository {
        return AnimalRepository(animalDao, petfinderService)
    }
}