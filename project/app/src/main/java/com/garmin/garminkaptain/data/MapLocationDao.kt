package com.garmin.garminkaptain.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MapLocationDao {

    @Insert
    suspend fun insertAllMapLocations(mapLocationList: List<MapLocation>)

}