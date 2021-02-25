package com.garmin.garminkaptain.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ReviewSummaryDao {

    @Insert
    suspend fun insertAllReviewSummary(reviewSummaryList: List<ReviewSummary>)

}