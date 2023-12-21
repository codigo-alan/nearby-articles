package com.example.nearbyarticles.data.remote

import com.example.nearbyarticles.domain.model.Data
import com.google.android.gms.common.api.Response
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiInterface {

    @GET("api.php")
    suspend fun getData(@QueryMap filters: Map<String, String>): retrofit2.Response<Data> //Obtain data from api

}