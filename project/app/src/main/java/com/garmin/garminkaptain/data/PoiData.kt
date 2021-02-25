package com.garmin.garminkaptain.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "poi_table")
data class PointOfInterest(
    @PrimaryKey val id: Long,
    @Embedded val mapLocation: MapLocation,
    val name: String,
    val poiType: String,
    @Embedded val reviewSummary: ReviewSummary
)

@Entity(tableName = "review_table")
data class Review(
    @PrimaryKey val id: Long,
    val rating: Double,
    val poiId: Long,
    val title: String,
    val comment: String,
    val date: Date
)

data class MapLocation(
    val latitude: Double,
    val longitude: Double
)

data class ReviewSummary(
    val averageRating: Double,
    val numberOfReviews: Int
)
