package com.garmin.garminkaptain.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.model.PoiRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PoiViewModel : ViewModel() {

    init {
        Log.d(TAG, "init called")
    }

    private val loadingLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private val poiReviewListLiveData: MutableLiveData<List<Review>> by lazy {
        MutableLiveData<List<Review>>()
    }

    private val poiListLiveData: MutableLiveData<List<PointOfInterest>> by lazy {
        MutableLiveData<List<PointOfInterest>>()
    }


    fun getPoi(id: Long): LiveData<PointOfInterest?> = liveData {
        loadingLiveData.postValue(true)
        PoiRepository.getPoi(id).collect {
            emit(it)
            loadingLiveData.postValue(false)
        }
    }

    fun getPoiList(): LiveData<List<PointOfInterest>> {
        loadPoiList()
        return poiListLiveData
    }

    fun getReviewList(poiId: Long): LiveData<List<Review>> {
        loadReviewList(poiId)
        return poiReviewListLiveData
    }

    fun loadReviewList(poiId: Long) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            PoiRepository.getReviewList(poiId).collect {
                poiReviewListLiveData.postValue(it)
                loadingLiveData.postValue(false)
            }
        }
    }

    fun loadPoiList() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            while(true) {
                PoiRepository.getPoiList().collect {
                    poiListLiveData.postValue(it)
                    loadingLiveData.postValue(false)
                }
                delay(5000)
            }
        }
    }

    fun getLoading(): LiveData<Boolean> = loadingLiveData

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared() called")
    }

}