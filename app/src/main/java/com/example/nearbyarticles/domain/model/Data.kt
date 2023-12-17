package com.example.nearbyarticles.domain.model

import com.google.gson.annotations.SerializedName

data class Data(
    val query: Pages,
)

data class Pages(
    @SerializedName("pages") val items: Map<String, Item>,
)
