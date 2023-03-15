package com.bignerdranch.android.androidtask1.task4.presenter

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bignerdranch.android.androidtask1.task4.view.MapsFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.*

@InjectViewState
class MapsPresenter(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : MvpPresenter<MapsContract>() {

    var map: GoogleMap? = null
//    var mapsFragment = MapsFragment()
    private var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null
    private val defaultLocation = LatLng(43.2, 76.8)

    fun generateRandomMarkers(googleMap: GoogleMap) {
        //set your own minimum distance here
        val minimumDistanceFromMe = 10
        //set your own maximum distance here
        val maximumDistanceFromMe = 10000
        //set number of markers you want to generate in Map/
        val markersToGenerate = 5
        for (position in 1..markersToGenerate) {
            generateRandomCoordinates(minimumDistanceFromMe, maximumDistanceFromMe, googleMap)
        }
    }

    @SuppressLint("MissingPermission")
    fun generateRandomCoordinates(min: Int, max: Int, googleMap: GoogleMap) {
        var coordinates: LatLng
        var currentLong: Double
        var currentLat: Double
        val meterCord = 0.00900900900901 / 1000
        //Generate random Meters between the maximum and minimum Meters
        val r = Random()
        val randomMeters: Int = r.nextInt(max + min)
        //then Generating Random numbers for different Methods
        val randomPM: Int = r.nextInt(6)
        val metersCordN = meterCord * randomMeters.toDouble()
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                CancellationTokenSource().token

            override fun isCancellationRequested() = false
        }).addOnSuccessListener{
            currentLong = it.longitude
            currentLat = it.latitude
            coordinates = when (randomPM) {
                0 -> LatLng(currentLat + metersCordN, currentLong + metersCordN)
                1 -> LatLng(currentLat - metersCordN, currentLong - metersCordN)
                2 -> LatLng(currentLat + metersCordN, currentLong - metersCordN)
                3 -> LatLng(currentLat - metersCordN, currentLong + metersCordN)
                4 -> LatLng(currentLat, currentLong - metersCordN)
                else -> LatLng(currentLat - metersCordN, currentLong)
            }
            googleMap.addMarker(
                MarkerOptions().position(
                    LatLng(
                        requireNotNull(coordinates.latitude),
                        requireNotNull(coordinates.longitude)
                    )
                ).title("mid point").snippet("Snippet")
            )
        }
    }
    @SuppressLint("MissingPermission")
     fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            val location = LatLng(
                                requireNotNull(lastKnownLocation).latitude,
                                requireNotNull(lastKnownLocation).longitude
                            )
                            map?.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    location, DEFAULT_ZOOM.toFloat()
                                )
                            )
                        }
                    } else {
                        map?.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat())
                        )
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }
    @SuppressLint("MissingPermission")
    fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                viewState.getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionGranted = true
                }
            }
            else -> onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        updateLocationUI()
    }

    companion object {
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

}