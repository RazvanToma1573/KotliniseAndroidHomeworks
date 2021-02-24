package com.garmin.garminkaptain.model

import android.util.Log
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.data.reviewList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PoiRepository {

    suspend fun getPoiList(): Flow<List<PointOfInterest>> = flow {
        delay(2000)
        emit(poiList)
    }

    suspend fun getPoi(id: Long): Flow<PointOfInterest?> = flow {
        delay(2000)
        emit(poiList.find { it.id == id })
    }

    suspend fun getReviewList(poiId: Long) = flow<List<Review>> {
        delay(2000)
        emit(reviewList.filter { it.poiId == poiId })
    }

}