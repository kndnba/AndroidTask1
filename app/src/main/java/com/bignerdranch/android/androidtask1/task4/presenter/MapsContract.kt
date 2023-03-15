package com.bignerdranch.android.androidtask1.task4.presenter

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MapsContract : MvpView {
    fun generateRandomMarkers(googleMap: GoogleMap)
    fun generateRandomCoordinates(min: Int, max: Int, googleMap: GoogleMap)
    fun getLocationPermission()
    fun updateLocationUI()
    fun getDeviceLocation()
    fun onRequestPermissionsResult(        requestCode: Int,
                                           permissions: Array<String>,
                                           grantResults: IntArray)
}