package com.garmin.garminkaptain.model

import android.util.Log
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PoiRepository(private val poiDao: PoiDao, private val reviewDao: ReviewDao) {

    fun getPoiList(): Flow<List<PointOfInterest>> = poiDao.getAllPoi()

    fun getPoi(id: Long): Flow<PointOfInterest> = poiDao.getPoi(id)

    fun getReviewList(poiId: Long): Flow<List<Review>> = reviewDao.getAllReview(poiId)

}