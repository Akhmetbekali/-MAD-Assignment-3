package com.example.assignment3.data.db


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimalDbModel::class, AnimalBreedDbModel::class, AnimalTypeDbModel::class], version = 1)
abstract class AnimalRoomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    abstract fun animalBreedDao() : AnimalBreedDao

    abstract fun animalTypeDao() : AnimalTypeDao
}
