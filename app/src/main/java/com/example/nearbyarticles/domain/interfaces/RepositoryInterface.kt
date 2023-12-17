package com.example.nearbyarticles.domain.interfaces

import com.example.nearbyarticles.data.remote.RemoteDataSource

interface RepositoryInterface {

    val remoteDataSource: RemoteDataSource
    fun remoteFetchData() //Get data from API
}