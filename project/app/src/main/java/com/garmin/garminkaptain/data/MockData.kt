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
        MapLocation(37.8180564724432, -122.52704143524173),
        "Point Bonita",
        "Anchorage",
        ReviewSummary(3.0, 2)
    ),
    PointOfInterest(
        12975,
        MapLocation(37.8770892291283, -122.503309249878),
        "Richardson Bay Marina",
        "Marina",
        ReviewSummary(5.0, 0)
    ),
    PointOfInterest(
        46085,
        MapLocation(37.82878469060811, -122.47633210712522),
        "Needles",
        "Anchorage",
        ReviewSummary(4.3, 1)
    ),
    PointOfInterest(
        19637,
        MapLocation(37.82077, -122.4786),
        "Golden Gate Bridge",
        "Bridge",
        ReviewSummary(0.0, 0)
    ),
    PointOfInterest(
        60928,
        MapLocation(37.8325155338083, -122.47500389814363),
        "Horseshoe Cove",
        "Anchorage",
        ReviewSummary(2.0, 2)
    ),
    PointOfInterest(
        39252,
        MapLocation(37.833886767314, -122.475371360779),
        "Presidio Yacht Club",
        "Marina",
        ReviewSummary(3.0, 5)
    ),
    PointOfInterest(
        25644,
        MapLocation(37.8673327691044, -122.435932159424),
        "Ayala Cove",
        "Anchorage",
        ReviewSummary(4.7, 18)
    ),
    PointOfInterest(
        61865,
        MapLocation(37.850002964208095, -122.41632213957898),
        "Tide Rips",
        "Hazard",
        ReviewSummary(0.0, 0)
    ),
    PointOfInterest(
        46713,
        MapLocation(37.827799573006274, -122.42648773017541),
        "Dangerous Rock",
        "Hazard",
        ReviewSummary(0.0, 0)
    ),
    PointOfInterest(
        57109,
        MapLocation(37.87572310328571, -122.50570595169079),
        "Woodrum Marine Boat Repair/Carpentry",
        "Business",
        ReviewSummary(0.0, 0)
    )
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