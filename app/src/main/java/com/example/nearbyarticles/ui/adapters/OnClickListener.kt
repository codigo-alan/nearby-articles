package com.example.nearbyarticles.ui.adapters

import com.example.nearbyarticles.domain.model.Item

interface OnClickListener {
    fun onClick(item: Item)
}