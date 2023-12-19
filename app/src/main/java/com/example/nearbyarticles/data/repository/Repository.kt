package com.example.nearbyarticles.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearbyarticles.data.remote.RemoteDataSource
import com.example.nearbyarticles.domain.interfaces.RepositoryInterface
import com.example.nearbyarticles.domain.model.Item
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(): RepositoryInterface {

    override val remoteDataSource = RemoteDataSource() //TODO inject Dagger Hilt

    private var _itemsRepo = remoteDataSource.itemsRemote
    var itemsRepo: LiveData<List<Item>> = _itemsRepo

    private var _successfulQuery = remoteDataSource.successfulQuery
    val successfulQuery: LiveData<Boolean?> = _successfulQuery

    override fun remoteFetchData(coordinates: LatLng) {
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.fetchData(coordinates) //call remote repo to get data from api
            //_itemsRepo.postValue(remoteDataSource.itemsRemote.value)
            Log.d("devApiRepoItems", "${itemsRepo.value}")
        }
    }

}