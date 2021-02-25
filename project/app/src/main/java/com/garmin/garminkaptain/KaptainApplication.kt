package com.garmin.garminkaptain

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.garmin.garminkaptain.data.PoiDatabase
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.data.reviewList
import com.garmin.garminkaptain.model.PoiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class KaptainApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { PoiDatabase.getInstance(applicationContext, applicationScope) }
    val repository by lazy { PoiRepository(database.getPoiDao(), database.getReviewDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}