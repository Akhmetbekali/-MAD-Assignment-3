package com.example.assignment3.data.db

import androidx.lifecycle.LiveData;
import androidx.room.*

@Dao
interface AnimalBreedDao {

    @Query("SELECT * from animal_breed_table WHERE type = :type")
    fun getAllBreedsByType(type : String) : LiveData<List<AnimalBreedDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breedDbModel: AnimalBreedDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breedDbModels : List<AnimalBreedDbModel>)

    @Query("DELETE FROM animal_breed_table")
    fun deleteAll()
}
