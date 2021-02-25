package com.garmin.garminkaptain.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert
    suspend fun insertReview(review: Review)

    @Insert
    suspend fun insertAllReview(reviewList: List<Review>)

    @Delete
    suspend fun deleteReview(review: Review)

    @Update
    suspend fun updateReview(review: Review)

    @Query("SELECT * from review_table WHERE poiId=:poiId")
    fun getAllReview(poiId: Long): Flow<List<Review>>

    @Query("SELECT * from review_table WHERE id=:id")
    fun getReview(id: Long): Flow<Review>
    
}