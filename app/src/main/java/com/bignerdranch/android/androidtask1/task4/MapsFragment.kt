package com.bignerdranch.android.androidtask1.task4

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bignerdranch.android.androidtask1.BuildConfig.GOOGLE_MAPS_API_KEY
import com.bignerdranch.android.androidtask1.R
import com.bignerdranch.android.androidtask1.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {
    private var map: GoogleMap? = null
    lateinit var binding: FragmentMapsBinding
    private val compositeDisposable = CompositeDisposable()
    private lateinit var placesClient: PlacesClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null
    private val defaultLocation = LatLng(43.2, 76.8)
    private val callback = OnMapReadyCallback {
        onMapReadyCallback(it)
    }

    private fun onMapReadyCallback(googleMap: GoogleMap){
        map = googleMap
        googleMap.addMarker(MarkerOptions().position(defaultLocation).title("Marker in Almaty"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))
        googleMap.setMinZoomPreference(12.0F)
        googleMap.setOnMarkerClickListener(this)
        // Prompt the user for permission.

        getLocationPermission()
        context?.checkBackgroundLocationPermissionAPI30(PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI()

        // Get the current location of the device and set the position of the map.
        getDeviceLocation()
        generateRandomMarkers(googleMap)

        googleMap.setOnMapClickListener {
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            googleMap.addMarker(markerOptions)
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Places.initialize(requireContext(), GOOGLE_MAPS_API_KEY)
        placesClient = Places.createClient(requireContext())

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        map?.animateCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    marker.position.latitude,
                    marker.position.longitude
                )
            )
        )
        return true
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
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
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        updateLocationUI()
    }

    private fun updateLocationUI() {
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
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
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

    private fun Context.checkSinglePermission(permission: String) : Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(30)
    private fun Context.checkBackgroundLocationPermissionAPI30(backgroundLocationRequestCode: Int) {
        if (checkSinglePermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) return
        AlertDialog.Builder(this)
            .setTitle(R.string.background_location_permission_title)
            .setMessage(R.string.background_location_permission_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                // this request will take user to Application's Setting page
                requestPermissions(arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), backgroundLocationRequestCode)
            }
            .setNegativeButton(R.string.no) { dialog,_ ->
                dialog.dismiss()
            }
            .create()
    }

    private fun generateRandomMarkers(googleMap: GoogleMap) {
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

    private fun generateRandomCoordinates(min: Int, max: Int, googleMap: GoogleMap) {
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
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            currentLong = it.result.longitude
            currentLat = it.result.latitude
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

    companion object {
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }
}