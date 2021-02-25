package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.data.reviewList
import com.garmin.garminkaptain.viewModel.PoiViewModel
import com.garmin.garminkaptain.viewModel.PoiViewModelFactory

class PoiReviewsFragment : Fragment(R.layout.poi_review_fragment) {

    private val args: PoiDetailsFragmentArgs by navArgs()

    inner class PoiReviewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView = itemView.findViewById<TextView>(R.id.poi_item_title_view)
        private val dateView = itemView.findViewById<TextView>(R.id.poi_item_date_view)
        private val commentView = itemView.findViewById<TextView>(R.id.poi_item_comment_view)
        private val ratingBar = itemView.findViewById<RatingBar>(R.id.poi_rating_view)

        fun bind(review: Review) {
            titleView.text = review.title
            dateView.text = review.date.toString()
            commentView.text = review.comment
            ratingBar.rating = review.rating.toFloat()
        }
    }

    inner class PoiReviewsAdapter : RecyclerView.Adapter<PoiReviewsItemViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PoiReviewsItemViewHolder {
            return PoiReviewsItemViewHolder(
                layoutInflater.inflate(R.layout.poi_review_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: PoiReviewsItemViewHolder, position: Int) {
            reviews.getOrNull(position)?.let {
                holder.bind(it)
            }
        }

        override fun getItemCount(): Int = reviews.size

    }

    private var reviews = listOf<Review>()
    private var adapter = PoiReviewsAdapter()
    private val viewModel: PoiViewModel by activityViewModels { PoiViewModelFactory((activity?.application as KaptainApplication).repository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.poi_reviews_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = this@PoiReviewsFragment.adapter
        }

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefreshReview)
        swipeRefreshLayout.setOnRefreshListener { viewModel.loadPoiList() }

        viewModel.getLoading()
            .observe(viewLifecycleOwner, Observer { swipeRefreshLayout.isRefreshing = it })

        viewModel.getReviewList(args.poiId).observe(viewLifecycleOwner, Observer {
            it?.let {
                reviews = it
                adapter.notifyDataSetChanged()
            }
        })
    }
}