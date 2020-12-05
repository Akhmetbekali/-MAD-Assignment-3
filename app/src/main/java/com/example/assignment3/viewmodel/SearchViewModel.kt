package com.example.assignment3.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.assignment3.data.AnimalRepository
import com.example.assignment3.data.db.AnimalDbModel
import com.example.assignment3.sharedpreferences.SharedPreferencesWrapper

import javax.inject.Inject
import javax.inject.Named

class SearchViewModel
@Inject constructor(
    application: Application,
    @Named("secure") var secureSharedPrefs: SharedPreferencesWrapper,
    val repository: AnimalRepository
) : AndroidViewModel(application) {

    val any = "Any"

    private val _allAnimals: MutableLiveData<List<AnimalDbModel>> = MutableLiveData()
    val allAnimals: LiveData<List<AnimalDbModel>> get() = _allAnimals

    private val _networkActive: MutableLiveData<Boolean> = MutableLiveData(true)
    val networkActive: LiveData<Boolean> get() = _networkActive

    private val _allTypes: MutableLiveData<List<String>> = MutableLiveData()
    val allTypes: LiveData<List<String>> get() = _allTypes

    private val _breeds: MutableLiveData<List<String>> = MutableLiveData()
    val breeds: LiveData<List<String>> get() = _breeds

    // TODO Improve don't forget use offline storage
    init {
        _networkActive.postValue(repository.testNetwork())
        _allAnimals.postValue(repository.getAllAnimals(null, null).value ?: emptyList())
        _allTypes.postValue(listOf(any).plus(repository.getAllTypes().value?.map { it -> it.name }
                ?: emptyList()))
        _breeds.postValue(listOf(any))
    }
    // TODO Improve
    fun selectType(type: String) {
        if (type == ""){
            _breeds.postValue(listOf(any))
        }
        else {
            _breeds.postValue(listOf(any).plus(repository.getAllBreeds(type).value?.map { it -> it.name }
                    ?: emptyList()))
        }
    }
        // TODO Improve
    fun findAnimals(type: String?, breed: String?) {
        _allAnimals.postValue(repository.getAllAnimals(type, breed).value)
    }
}

