package com.example.nearbyarticles.data.repository

import com.example.nearbyarticles.data.remote.RemoteDataSource
import com.example.nearbyarticles.domain.interfaces.RepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(): RepositoryInterface {

    override val remoteDataSource = RemoteDataSource() //TODO inject Dagger Hilt

    override fun remoteFetchData() {
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.fetchData() //call remote repo to get data from api
        }
    }

}