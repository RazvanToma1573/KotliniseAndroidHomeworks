package com.garmin.garminkaptain.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity(tableName = "poi_table")
data class PointOfInterest(
    @PrimaryKey val id: Long,
    val name: String,
    val poiType: String,
)

data class PointOfInterestAndMapLocationAndReviewSummary(
    @Embedded val pointOfInterest: PointOfInterest,
    @Relation(
        parentColumn = "id",
        entityColumn = "pointOfInterestId"
    )
    val mapLocation: MapLocation,
    @Relation(
        parentColumn = "id",
        entityColumn = "pointOfInterestId"
    )
    val reviewSummary: ReviewSummary
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

@Entity(tableName = "map_location_table", primaryKeys = ["latitude", "longitude"])
data class MapLocation(
    val latitude: Double,
    val longitude: Double,
    val pointOfInterestId: Long
)

@Entity(tableName = "review_summary_table", primaryKeys = ["averageRating", "numberOfReviews", "pointOfInterestId"])
data class ReviewSummary(
    val averageRating: Double,
    val numberOfReviews: Int,
    val pointOfInterestId: Long
)
