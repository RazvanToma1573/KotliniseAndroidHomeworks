package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.data.reviewList

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

    private val allReviews = reviewList
    private lateinit var reviews: List<Review>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reviews = allReviews.filter { it.poiId == args.poiId }
        view.findViewById<RecyclerView>(R.id.poi_reviews_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PoiReviewsAdapter()
        }
    }
}