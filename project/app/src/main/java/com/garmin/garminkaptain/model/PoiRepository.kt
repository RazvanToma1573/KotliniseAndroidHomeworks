package com.garmin.garminkaptain.model

import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.data.reviewList

object PoiRepository {

    fun getReviewList(poiId: Long) = reviewList.filter { it.poiId == poiId }

    fun getPoiList() = poiList

    fun getPoi(id: Long) = poiList.find { it.id == id }
}