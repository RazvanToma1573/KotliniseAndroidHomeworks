package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.garmin.garminkaptain.KaptainApplication
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.data.PointOfInterestAndMapLocationAndReviewSummary
import com.garmin.garminkaptain.viewModel.PoiViewModel
import com.garmin.garminkaptain.viewModel.PoiViewModelFactory


class PoiListFragment : Fragment(R.layout.poi_list_fragment) {

    inner class PoiListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameView = itemView.findViewById<TextView>(R.id.poi_item_name_view)
        private val typeView = itemView.findViewById<TextView>(R.id.poi_item_type_view)

        fun bind(poi: PointOfInterestAndMapLocationAndReviewSummary) {
            nameView.text = poi.pointOfInterest.name
            typeView.text = poi.pointOfInterest.poiType
            itemView.setOnClickListener {
                findNavController().navigate(
                    PoiListFragmentDirections.actionPoiListFragmentToPoiDetailsFragment(poi.pointOfInterest.id)
                )
            }
        }
    }

    inner class PoiListAdapter : RecyclerView.Adapter<PoiListItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiListItemViewHolder {
            return PoiListItemViewHolder(
                layoutInflater.inflate(R.layout.poi_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: PoiListItemViewHolder, position: Int) {
            pointsOfInterest.getOrNull(position)?.let {
                holder.bind(it)
            }
        }

        override fun getItemCount(): Int = pointsOfInterest.size
    }

    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            Toast.makeText(context, "on Move", Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            viewModel.removePoi(pointsOfInterest[position])
            Toast.makeText(context, pointsOfInterest[position].pointOfInterest.name + " deleted", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }
    }

    private var pointsOfInterest = listOf<PointOfInterestAndMapLocationAndReviewSummary>()
    private var adapter = PoiListAdapter()
    private val viewModel: PoiViewModel by activityViewModels { PoiViewModelFactory((activity?.application as KaptainApplication).repository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.poi_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = this@PoiListFragment.adapter
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(view.findViewById(R.id.poi_list))

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeRefreshLayout.setOnRefreshListener { viewModel.loadPoiList() }

        viewModel.getLoading()
            .observe(viewLifecycleOwner, Observer { swipeRefreshLayout.isRefreshing = it })

        viewModel.getPoiList().observe(viewLifecycleOwner, Observer {
            it?.let {
                pointsOfInterest = it
                adapter.notifyDataSetChanged()
            }
        })
    }

}