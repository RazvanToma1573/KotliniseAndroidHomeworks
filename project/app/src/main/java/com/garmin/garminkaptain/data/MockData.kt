package com.garmin.garminkaptain.data

import java.util.*

val calendar: Calendar = Calendar.getInstance()

fun setCalendar(year: Int, month: Int, day: Int): Date {
    calendar.set(year, month, day)
    return calendar.time
}

val poiList: List<PointOfInterest> = listOf(
    PointOfInterest(
        46067,
        "Point Bonita",
        "Anchorage"
    ),
    PointOfInterest(
        12975,
        "Richardson Bay Marina",
        "Marina"
    ),
    PointOfInterest(
        46085,
        "Needles",
        "Anchorage"
    ),
    PointOfInterest(
        19637,
        "Golden Gate Bridge",
        "Bridge"
    ),
    PointOfInterest(
        60928,
        "Horseshoe Cove",
        "Anchorage"
    ),
    PointOfInterest(
        39252,
        "Presidio Yacht Club",
        "Marina",
    ),
    PointOfInterest(
        25644,
        "Ayala Cove",
        "Anchorage"
    ),
    PointOfInterest(
        61865,
        "Tide Rips",
        "Hazard"
    ),
    PointOfInterest(
        46713,
        "Dangerous Rock",
        "Hazard"
    ),
    PointOfInterest(
        57109,
        "Woodrum Marine Boat Repair/Carpentry",
        "Business"
    )
)

val mapLocationList: List<MapLocation> = listOf(
    MapLocation(37.8180564724432, -122.52704143524173, 46067),
    MapLocation(37.8770892291283, -122.503309249878, 12975),
    MapLocation(37.82878469060811, -122.47633210712522, 46085),
    MapLocation(37.82077, -122.4786, 19637),
    MapLocation(37.8325155338083, -122.47500389814363, 60928),
    MapLocation(37.833886767314, -122.475371360779, 39252),
    MapLocation(37.8673327691044, -122.435932159424, 25644),
    MapLocation(37.850002964208095, -122.41632213957898, 61865),
    MapLocation(37.827799573006274, -122.42648773017541, 46713),
    MapLocation(37.87572310328571, -122.50570595169079, 57109)
)

val reviewSummaryList: List<ReviewSummary> = listOf(
    ReviewSummary(3.0, 2, 46067),
    ReviewSummary(5.0, 0, 12975),
    ReviewSummary(4.3, 1, 46085),
    ReviewSummary(0.0, 0, 19637),
    ReviewSummary(2.0, 2, 60928),
    ReviewSummary(3.0, 5, 39252),
    ReviewSummary(4.7, 18, 25644),
    ReviewSummary(0.0, 0, 61865),
    ReviewSummary(0.0, 0, 46713),
    ReviewSummary(0.0, 0, 57109)
)

val reviewList: List<Review> = listOf(
    Review(
        12843,
        3.7,
        46067,
        "Go to Point Bonita in the summer",
        "Truly a majestic place. Everyone should have the change to see" +
                "this beautiful place at least once in their lifetime.",
        setCalendar(2018, 6, 22)
    ),
    Review(
        13543,
        2.3,
        46067,
        "Don't go to Point Bonita in the summer",
        "Didn't like it...",
        setCalendar(2019,6,18)
    ),
    Review(
        12312,
        4.3,
        46085,
        "Go to Needles in the winter",
        "It's pretty cool!",
        setCalendar(2020,6,18)
    ),
    Review(
        11312,
        2.0,
        60928,
        "Don't go to Horseshoe Cove in the winter",
        "meh",
        setCalendar(2020,6,18)
    ),
    Review(
        14312,
        2.0,
        60928,
        "Don't go to Horseshoe Cove in the winter",
        "Not really interesting...",
        setCalendar(2020,6,18)
    ),
)
