package com.example.assignment3.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "animal_breed_table")
data class AnimalBreedDbModel(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type : String
): Parcelable

