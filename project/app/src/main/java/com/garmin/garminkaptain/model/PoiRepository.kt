package com.garmin.garminkaptain.model

import android.util.Log
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.data.reviewList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PoiRepository {

    fun getPoiList(application: KaptainApplication): Flow<List<PointOfInterest>> = application.poiDatabase.getPoiDao().getAllPoi()

    fun getPoi(application: KaptainApplication, id: Long): Flow<PointOfInterest> = application.poiDatabase.getPoiDao().getPoi(id)

    fun getReviewList(application: KaptainApplication, poiId: Long) : Flow<List<Review>> = application.poiDatabase.getReviewDao().getAllReview(poiId)

}