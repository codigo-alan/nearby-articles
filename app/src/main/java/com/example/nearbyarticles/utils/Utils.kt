package com.example.nearbyarticles.utils

fun String.limitLength(maxLength: Int): String {
    return if (this.length > maxLength) "${this.substring(0, maxLength)}..."
    else this

}