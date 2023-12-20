package com.example.nearbyarticles.utils

import com.google.android.gms.maps.model.LatLng
import kotlin.math.*

fun String.limitLength(maxLength: Int): String {
    return if (this.length > maxLength) "${this.substring(0, maxLength)}..."
    else this

}

fun haversine(coordinatesFirst: LatLng, coordinatesSecond: LatLng): Double {

    //Earth radio km
    val R = 6371.0

    //decimals degrees into radians
    val lat1Rad = Math.toRadians(coordinatesFirst.latitude)
    val lon1Rad = Math.toRadians(coordinatesFirst.longitude)
    val lat2Rad = Math.toRadians(coordinatesSecond.latitude)
    val lon2Rad = Math.toRadians(coordinatesSecond.longitude)

    //lat and long differences
    val dLat = lat2Rad - lat1Rad
    val dLon = lon2Rad - lon1Rad

    //haversine
    val a = sin(dLat / 2).pow(2) + cos(lat1Rad) * cos(lat2Rad) * sin(dLon / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    //return distance in km
    return R * c
}