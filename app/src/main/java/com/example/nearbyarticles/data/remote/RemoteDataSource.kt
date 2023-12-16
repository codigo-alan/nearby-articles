package com.example.nearbyarticles.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nearbyarticles.domain.model.Item

class RemoteDataSource {

    private val apiInterface = ApiInterface.create() //TODO Dagger Hilt inject
    var itemsRemote = MutableLiveData<List<Item>>()
    suspend fun fetchData() {
        val response = apiInterface.getData()

        if(response.isSuccessful) {
            itemsRemote.postValue(response.body()!!.query.items)
            Log.d("apiResponse", "${response.body()!!.query.items}") //for dev
        }
        else {
            itemsRemote.postValue(listOf())
            Log.d("apiResponseFail", "${response.errorBody()}") //for dev
        }
    }

}