package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.viewModel.PoiViewModel
import com.garmin.garminkaptain.viewModel.PoiViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class PoiMapFragment : Fragment(R.layout.poi_map_fragment), GoogleMap.OnInfoWindowClickListener {

    private var pointsOfInterest = listOf<PointOfInterest>()
    private val viewModel: PoiViewModel by activityViewModels { PoiViewModelFactory((activity?.application as KaptainApplication).repository) }
    private lateinit var mapFragment: SupportMapFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        viewModel.getPoiList().observe(viewLifecycleOwner, Observer {
            it?.let {
                pointsOfInterest = it
                refreshMap()
            }
        })
    }

    override fun onInfoWindowClick(selectedMarker: Marker?) {
        selectedMarker?.let { marker ->
            val poi = pointsOfInterest.find {
                it.mapLocation.latitude == marker.position.latitude && it.mapLocation.longitude == marker.position.longitude
            }
            poi?.let {
                findNavController().navigate(
                    PoiMapFragmentDirections.actionPoiMapFragmentToPoiDetailsFragment(it.id)
                )
            }
        }
    }

    private fun refreshMap() {
        mapFragment.getMapAsync { map ->
            map.setOnInfoWindowClickListener(this)
            val latLngBoundsBuilder = LatLngBounds.builder()
            pointsOfInterest.forEach { poi ->
                LatLng(poi.mapLocation.latitude, poi.mapLocation.longitude).also {
                    latLngBoundsBuilder.include(it)
                    map.addMarker(MarkerOptions().position(it).title(poi.name))
                }
            }
            map.animateCamera(
                CameraUpdateFactory.newLatLngBounds(latLngBoundsBuilder.build(), PADDING)
            )
        }
    }

    companion object {
        private const val PADDING = 100
    }
}