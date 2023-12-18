package com.example.nearbyarticles.domain.interfaces

import com.example.nearbyarticles.data.remote.RemoteDataSource
import com.google.android.gms.maps.model.LatLng

interface RepositoryInterface {

    val remoteDataSource: RemoteDataSource
    fun remoteFetchData(coordinates: LatLng) //Get data from API
}