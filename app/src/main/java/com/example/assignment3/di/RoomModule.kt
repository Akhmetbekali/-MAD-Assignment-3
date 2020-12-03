package com.example.assignment3.di

import android.content.Context
import androidx.room.Room
import com.example.assignment3.data.db.AnimalDao
import com.example.assignment3.data.db.AnimalRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(context: Context) {

    private val animalDatabase: AnimalRoomDatabase =
        Room.databaseBuilder(context, AnimalRoomDatabase::class.java, "animal_database")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun providesRoomDatabase(): AnimalRoomDatabase {
        return animalDatabase
    }

    @Singleton
    @Provides
    fun providesAnimalDao(): AnimalDao = animalDatabase.animalDao()
}