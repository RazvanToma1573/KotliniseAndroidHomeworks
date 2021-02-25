package com.garmin.garminkaptain.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PointOfInterest::class, Review::class], version = 1)
@TypeConverters(Converters::class)
abstract class PoiDatabase : RoomDatabase() {
    abstract fun getPoiDao(): PoiDao
    abstract fun getReviewDao(): ReviewDao
}