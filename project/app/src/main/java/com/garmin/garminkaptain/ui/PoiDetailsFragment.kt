package com.garmin.garminkaptain.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.viewModel.PoiViewModel
import com.garmin.garminkaptain.viewModel.PoiViewModelFactory

class PoiDetailsFragment : Fragment() {

    private val args: PoiDetailsFragmentArgs by navArgs()

    // Creates a single ViewModel per activity
    //private val viewModel: PoiViewModel by activityViewModels()

    // Creates a different ViewModel for each Fragment
    private val viewModel: PoiViewModel by viewModels { PoiViewModelFactory((activity?.application as KaptainApplication).repository) }

    private lateinit var progressBar: ProgressBar
    private lateinit var group: Group

    private lateinit var nameTextView: TextView
    private lateinit var typeTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var numReviewsTextView: TextView
    private lateinit var reviewsButton: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: called")
        return inflater.inflate(R.layout.poi_details_fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: called")

        view.apply {
            nameTextView = findViewById(R.id.poi_name_view)
            typeTextView = findViewById(R.id.poi_type_view)
            ratingTextView = findViewById(R.id.poi_rating_view)
            numReviewsTextView = findViewById(R.id.poi_num_reviews_view)
            reviewsButton = findViewById(R.id.poi_view_reviews_button)
            progressBar = findViewById(R.id.poi_progress)
            group = findViewById(R.id.poi_details_group)
        }

        viewModel.getLoading().observe(
            viewLifecycleOwner,
            Observer {
                progressBar.visibility = if (it) VISIBLE else GONE
            })

        viewModel.getPoi(args.poiId).observe(viewLifecycleOwner, Observer { poi ->
            poi?.let {
                group.visibility = VISIBLE
                nameTextView.text = poi.name
                typeTextView.text = poi.poiType
                ratingTextView.text =
                    getString(R.string.label_rating, poi.reviewSummary.averageRating)
                numReviewsTextView.text =
                    getString(R.string.label_num_reviews, poi.reviewSummary.numberOfReviews)
                reviewsButton.setOnClickListener {
                    findNavController().navigate(
                        PoiDetailsFragmentDirections.actionPoiDetailsFragmentToPoiReviewsFragment(poi.id)
                    )
                }
                reviewsButton.isEnabled = poi.reviewSummary.numberOfReviews > 0
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach() called")
    }
}