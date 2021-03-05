package com.garmin.garminkaptain.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [PointOfInterest::class, Review::class], version = 1)
abstract class PoiDatabase : RoomDatabase() {
    abstract fun getPoiDao(): PoiDao
}