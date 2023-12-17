package com.example.nearbyarticles.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nearbyarticles.domain.model.Item

class RemoteDataSource {

    private val apiInterface = ApiInterface.create() //TODO Dagger Hilt inject
    var itemsRemote = MutableLiveData<List<Item>>()
    suspend fun fetchData() {

        val filtersMap = mapOf(
            "action" to "query",
            "format" to "json",
            "generator" to "geosearch",
            "prop" to "coordinates|pageimages",
            "ggscoord" to "39.46975|-0.37739")

        val response = apiInterface.getData(filtersMap)

        if(response.isSuccessful) {
            Log.d("apiResponse", "${response.body()}") //for dev
            val itemsList = mutableListOf<Item>()
            response.body()?.query?.items?.forEach { (itemId, item) -> itemsList += item }
            itemsRemote.postValue(itemsList)
            Log.d("apiResponseItems", "${itemsList}")
        }
        else {
            itemsRemote.postValue(listOf())
            Log.d("apiResponseFail", "${response.errorBody()}") //for dev
        }
    }

}