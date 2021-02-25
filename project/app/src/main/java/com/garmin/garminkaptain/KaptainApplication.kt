package com.garmin.garminkaptain

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.garmin.garminkaptain.data.PoiDatabase
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.data.reviewList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KaptainApplication : Application() {

    lateinit var poiDatabase: PoiDatabase

    val roomListener = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            GlobalScope.launch {
                poiDatabase.getPoiDao().insertAllPoi(poiList)
            }

            GlobalScope.launch {
                poiDatabase.getReviewDao().insertAllReview(reviewList)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        poiDatabase = Room.databaseBuilder(applicationContext, PoiDatabase::class.java, "poi-database").addCallback(roomListener).build()
    }
}