package com.garmin.garminkaptain.model

import android.util.Log
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.*
import com.garmin.garminkaptain.network.MockWebservice
import com.garmin.garminkaptain.network.Webservice
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class PoiRepository(
    private val database: PoiDatabase,
    private val webservice: Webservice = MockWebservice(),
) {

    private val dataIsStale = true

    suspend fun getPoiList(bbBox: MapBoundingBox): List<PointOfInterest> {
        var result: List<PointOfInterest>? = null
        val cacheList = database.getPoiDao().getAllPoi()
        result = cacheList

        if (cacheList.isEmpty() || dataIsStale) {
            val response = webservice.getPoiList(bbBox).execute()
            if (response.isSuccessful) {
                val data = response.body()?.pointsOfInterest
                if (data != null) {
                    result = data
                }
            }
        }

        return result ?: throw Exception("Empty Data")
    }


    fun getPoi(id: Long): Flow<PointOfInterest> {
        return database.getPoiDao().getPoi(id)
    }


    suspend fun getReviews(id: Long): List<Review> {
        return database.getPoiDao().getPoiWithReviews(id).reviews
    }
}