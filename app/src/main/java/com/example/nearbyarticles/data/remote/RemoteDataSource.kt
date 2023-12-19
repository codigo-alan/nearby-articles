package com.example.nearbyarticles.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearbyarticles.domain.model.Item
import com.google.android.gms.maps.model.LatLng

class RemoteDataSource {

    private val apiInterface = ApiInterface.create() //TODO Dagger Hilt inject

    private var _itemsRemote = MutableLiveData<List<Item>>().apply { value = listOf() }
    var itemsRemote: LiveData<List<Item>> = _itemsRemote

    private val _successfulQuery = MutableLiveData<Boolean?>().apply { value = null }
    val successfulQuery: LiveData<Boolean?> = _successfulQuery
    suspend fun fetchData(coordinates: LatLng) {

        val filtersMap = mapOf(
            "action" to "query",
            "format" to "json",
            "generator" to "geosearch",
            "prop" to "coordinates|pageimages",
            "ggscoord" to "${coordinates.latitude}|${coordinates.longitude}")

        Log.d("devMap", "${filtersMap["ggscoord"]}")
        //39.46975|-0.37739
        //${coordinates.latitude}|${coordinates.longitude}

        val response = apiInterface.getData(filtersMap)

        if(response.isSuccessful) {
            val itemsList = mutableListOf<Item>()
            response.body()?.query?.items?.forEach { (itemId, item) -> itemsList += item }
            _itemsRemote.postValue(itemsList)
            _successfulQuery.postValue(true)
            Log.d("devApiResponseItemsList", "${itemsList}")
            Log.d("devApiResponseItems", "${itemsRemote.value}")
        }
        else {
            _itemsRemote.postValue(listOf())
            _successfulQuery.postValue(false)
            Log.d("devApiResponseFail", "${response.errorBody()}")
        }

    }

}