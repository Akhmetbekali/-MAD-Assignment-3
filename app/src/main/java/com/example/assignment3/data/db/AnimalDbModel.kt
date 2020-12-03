package com.example.assignment3.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "animal_table")
data class AnimalDbModel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "organization_id") val organizationId: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "photoSmall") val photoSmallUrl: String,
    @ColumnInfo(name = "photoFull") val photoFullUrl: String
): Parcelable

