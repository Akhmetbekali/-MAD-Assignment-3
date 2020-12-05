package com.example.assignment3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment3.data.db.AnimalBreedDbModel
import com.example.assignment3.data.db.AnimalDao
import com.example.assignment3.data.db.AnimalDbModel
import com.example.assignment3.data.db.AnimalTypeDbModel
import com.example.assignment3.data.model.Animal
import com.example.assignment3.data.model.AnimalBreed
import com.example.assignment3.data.model.AnimalType

import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class AnimalRepository(
    private val animalDao: AnimalDao,
    var petFinderService: PetfinderService
) {

    fun testNetwork(): Boolean = petFinderService.getAnimals(null.toString(), null.toString())
        .map {
            true
        }.onErrorReturn {
            false
        }.blockingGet()

    //Improve with cashing logic.
    fun getAllAnimals(type : String?, breed : String?): LiveData<List<AnimalDbModel>> {
        return petFinderService.getAnimals(type, breed)
            .subscribeOn(Schedulers.io())
            .map {
                val animals = it.animalsArray.map { a -> mapper(a) }
                CoroutineScope(EmptyCoroutineContext).launch {
                    // TODO Improve store to DB
                }
                MutableLiveData(animals) as LiveData<List<AnimalDbModel>>
            }.blockingGet()

    }

    fun getAllTypes(): LiveData<List<AnimalTypeDbModel>> {
        return petFinderService.getTypes()
                .subscribeOn(Schedulers.io())
                .map {
                    val types = it.typesString.map { a -> typemapper(a) }
                    CoroutineScope(EmptyCoroutineContext).launch {
                        // TODO Improve store to DB
                    }
                    MutableLiveData(types) as LiveData<List<AnimalTypeDbModel>>
                }.blockingGet()
    }

    fun getAllBreeds(type: String): LiveData<List<AnimalBreedDbModel>> {
        return petFinderService.getBreeds(type)
                .subscribeOn(Schedulers.io())
                .map {
                    val breeds = it.breedsString.map { a -> breedmapper(a) }
                    CoroutineScope(EmptyCoroutineContext).launch {
                        // TODO Improve store to DB
                    }
                    MutableLiveData(breeds) as LiveData<List<AnimalBreedDbModel>>
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

    private fun breedmapper(animalBreed: AnimalBreed): AnimalBreedDbModel =
            AnimalBreedDbModel(
                    name = animalBreed.breed  ?: ""
            )
}
