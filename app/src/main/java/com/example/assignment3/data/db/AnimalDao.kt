package com.example.assignment3.data.db


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface AnimalDao {

    @Query("SELECT * from animal_table ORDER BY name ASC")
    fun getAlphabetizedAnimals(): LiveData<List<AnimalDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(animalDbModel: AnimalDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animalDbModels: List<AnimalDbModel>)

    @Query("DELETE FROM animal_table")
    fun deleteAll()
}
