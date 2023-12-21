package com.example.nearbyarticles.di

import com.example.nearbyarticles.data.remote.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        val baseUrl = "https://en.wikipedia.org/w/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }

}