package com.example.nearbyarticles.domain.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("pageid") val id: Long,
    val title: String,
    val coordinates: List<CoordinateApi>,
    @SerializedName("pageimage") val image: String,
)
