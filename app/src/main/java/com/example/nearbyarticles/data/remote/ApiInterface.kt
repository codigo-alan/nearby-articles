package com.example.nearbyarticles.data.remote

import com.example.nearbyarticles.domain.model.Data
import com.google.android.gms.common.api.Response
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("?action=query&generator=geosearch&prop=coordinates%7Cpageimages&ggscoord=39.46975%7C-0.37739")
    suspend fun getData(): retrofit2.Response<Data>
    //suspend fun getData(@QueryMap filters: Map<String, String>): retrofit2.Response<Data> //Obtain data from api
    companion object {

        private const val BASE_URL = "https://en.wikipedia.org/w/api.php/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder()

                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

    }

}