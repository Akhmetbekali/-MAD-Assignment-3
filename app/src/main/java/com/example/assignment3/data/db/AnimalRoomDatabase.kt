package com.example.assignment3.data.db


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimalDbModel::class], version = 1)
abstract class AnimalRoomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao
}
