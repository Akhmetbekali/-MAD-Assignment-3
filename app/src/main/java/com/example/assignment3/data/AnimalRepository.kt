package com.example.assignment3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment3.data.db.AnimalDao
import com.example.assignment3.data.db.AnimalDbModel
import com.example.assignment3.data.model.Animal

import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class AnimalRepository(
    private val animalDao: AnimalDao,
    var petFinderService: PetfinderService
) {

    fun testNetwork(): Boolean = petFinderService.getAnimals()
        .map {
            true
        }.onErrorReturn {
            false
        }.blockingGet()

    //Improve with cashing logic.
    fun getAllAnimals(): LiveData<List<AnimalDbModel>> {
        return petFinderService.getAnimals()
            .subscribeOn(Schedulers.io())
            .map {
                val animals = it.animalsArray.map { a -> mapper(a) }
                CoroutineScope(EmptyCoroutineContext).launch {
                    // TODO Improve store to DB
                }
                MutableLiveData(animals) as LiveData<List<AnimalDbModel>>
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
}
