package com.example.assignment3.data.db

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface AnimalTypeDao {

    @Query("SELECT * from animal_type_table ORDER BY name")
    fun getAllTypes() : LiveData<List<AnimalTypeDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animalTypeDbModel: AnimalTypeDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animalTypeDbModel : List<AnimalTypeDbModel>)

    @Query("DELETE FROM animal_type_table")
    fun deleteAll()
}
