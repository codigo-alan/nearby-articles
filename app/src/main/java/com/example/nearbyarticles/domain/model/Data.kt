package com.example.nearbyarticles.domain.model

data class Data(
    val query: Pages,
)

data class Pages(
    val items: List<Item>,
)
