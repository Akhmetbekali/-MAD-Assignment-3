package com.example.assignment3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment3.data.db.*
import com.example.assignment3.data.model.Animal
import com.example.assignment3.data.model.AnimalBreed
import com.example.assignment3.data.model.AnimalType

import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class AnimalRepository(
    private val animalDao: AnimalDao,
    private val animalTypeDao: AnimalTypeDao,
    private val animalBreedDao: AnimalBreedDao,
    var petFinderService: PetfinderService
) {

    fun testNetwork(): Boolean = petFinderService.getAnimals()
        .map {
            true
        }.onErrorReturn {
            false
        }.blockingGet()

    // TODO Improve with cashing logic.
    fun getAllAnimals(type : String?, breed : String?): LiveData<List<AnimalDbModel>> {
        return petFinderService.getAnimals(type, breed)
                    .subscribeOn(Schedulers.io())
                    .map {
                        val animals = it.animalsArray.map { a -> mapper(a) }
                        CoroutineScope(EmptyCoroutineContext).launch {
                            animalDao.insert(animals)
                            // TODO Improve store to DB
                        }
                        MutableLiveData(animals) as LiveData<List<AnimalDbModel>>
                    }
                .onErrorReturn {
                    animalDao.getAlphabetizedAnimals()
                }
                .blockingGet()
    }

    fun getAllTypes(): LiveData<List<AnimalTypeDbModel>> {
        return petFinderService.getTypes()
                    .subscribeOn(Schedulers.io())
                    .map {
                        val types = it.typesString.map { a -> typemapper(a) }
                        CoroutineScope(EmptyCoroutineContext).launch {
                            animalTypeDao.insert((types))
                            // TODO Improve store to DB
                        }
                        MutableLiveData(types) as LiveData<List<AnimalTypeDbModel>>
                    }.onErrorReturn {
                    animalTypeDao.getAllTypes()
                }
                .blockingGet()

    }

    fun getAllBreeds(type: String): LiveData<List<AnimalBreedDbModel>> {
        return petFinderService.getBreeds(type)
                    .subscribeOn(Schedulers.io())
                    .map {
                        val breeds = it.breedsString.map { a -> breedmapper(a, type) }
                        CoroutineScope(EmptyCoroutineContext).launch {
                            animalBreedDao.insert(breeds)
                            // TODO Improve store to DB
                        }
                        MutableLiveData(breeds) as LiveData<List<AnimalBreedDbModel>>
                    }.onErrorReturn {
                animalBreedDao.getAllBreedsByType(type)
            }.blockingGet()
    }

    private fun mapper(animal: Animal): AnimalDbModel =
        AnimalDbModel(
            id = animal.id ?: -1,
            organizationId = animal.organizationId,
            type = animal.type ?: "",
            breed = animal.breed?.breed ?: "",
            age = animal.age,
            gender = animal.gender ?: "",
            size = animal.size ?: "",
            name = animal.name ?: "",
            description = animal.description ?: "",
            photoFullUrl = animal.photos?.firstOrNull()?.photoFull ?: "",
            photoSmallUrl = animal.photos?.firstOrNull()?.photoSmall ?: ""
        )

    private fun typemapper(animalType: AnimalType): AnimalTypeDbModel =
            AnimalTypeDbModel(
                    name = animalType.name  ?: ""
            )

    private fun breedmapper(animalBreed: AnimalBreed, type: String): AnimalBreedDbModel =
            AnimalBreedDbModel(
                    name = animalBreed.breed  ?: "",
                    type = type
            )
}
