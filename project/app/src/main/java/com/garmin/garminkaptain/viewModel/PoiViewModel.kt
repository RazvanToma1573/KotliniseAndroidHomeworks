package com.garmin.garminkaptain.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.data.PointOfInterestAndMapLocationAndReviewSummary
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.model.PoiRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PoiViewModel(private val repository: PoiRepository) : ViewModel() {

    init {
        Log.d(TAG, "init called")
        viewModelScope.launch {
            while (true) {
                Log.d(TAG, "getPoiList() every 5 seconds")
                repository.getPoiList().collect {
                    poiListLiveData.postValue(it)
                    loadingLiveData.postValue(false)
                }
                delay(5000)
            }
        }
    }

    private val loadingLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private val poiReviewListLiveData: MutableLiveData<List<Review>> by lazy {
        MutableLiveData<List<Review>>()
    }

    private val poiListLiveData: MutableLiveData<List<PointOfInterestAndMapLocationAndReviewSummary>> by lazy {
        MutableLiveData<List<PointOfInterestAndMapLocationAndReviewSummary>>()
    }


    fun getPoi(id: Long): LiveData<PointOfInterestAndMapLocationAndReviewSummary?> = liveData {
        loadingLiveData.postValue(true)
        repository.getPoi(id).collect {
            emit(it)
            loadingLiveData.postValue(false)
        }
    }

    fun getPoiList(): LiveData<List<PointOfInterestAndMapLocationAndReviewSummary>> {
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
            repository.getReviewList(poiId).collect {
                poiReviewListLiveData.postValue(it)
                loadingLiveData.postValue(false)
            }
        }
    }

    fun loadPoiList() {
        Log.d(TAG, "loadPoiList() called")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            repository.getPoiList().collect {
                poiListLiveData.postValue(it)
                loadingLiveData.postValue(false)
            }
        }
    }

    fun getLoading(): LiveData<Boolean> = loadingLiveData

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared() called")
    }

}

class PoiViewModelFactory(private val repository: PoiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PoiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PoiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}