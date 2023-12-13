package com.example.nearbyarticles.domain.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("pageid") val id: Int,
    val title: String,
    @SerializedName("pageimage") val image: String,
)
