package com.garmin.garminkaptain.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [PointOfInterest::class, Review::class, MapLocation::class, ReviewSummary::class], version = 1)
@TypeConverters(Converters::class)
abstract class PoiDatabase : RoomDatabase() {
    abstract fun getPoiDao(): PoiDao
    abstract fun getReviewDao(): ReviewDao
    abstract fun getMapLocationDao(): MapLocationDao
    abstract fun getReviewSummaryDao(): ReviewSummaryDao

    private class PoiDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.getPoiDao(), database.getReviewDao(), database.getMapLocationDao(), database.getReviewSummaryDao())
                }
            }
        }

        suspend fun populateDatabase(poiDao: PoiDao, reviewDao: ReviewDao, mapLocationDao: MapLocationDao, reviewSummaryDao: ReviewSummaryDao) {
            mapLocationDao.insertAllMapLocations(mapLocationList)
            reviewSummaryDao.insertAllReviewSummary(reviewSummaryList)
            poiDao.insertAllPoi(poiList)
            reviewDao.insertAllReview(reviewList)
        }
    }

    companion object {
        private var INSTANCE: PoiDatabase? = null
        fun getInstance(context: Context, scope: CoroutineScope): PoiDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    PoiDatabase::class.java,
                    "poi_database"
                )
                    .addCallback(PoiDatabaseCallback(scope))
                    .build()
            }

            return INSTANCE as PoiDatabase
        }
    }
}