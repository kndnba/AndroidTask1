package com.bignerdranch.android.androidtask1.task4.view

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
import com.bignerdranch.android.androidtask1.task3.presenter.NewsPresenter
import com.bignerdranch.android.androidtask1.task4.presenter.MapsContract
import com.bignerdranch.android.androidtask1.task4.presenter.MapsPresenter
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
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*

class MapsFragment : MvpAppCompatFragment(), MapsContract, GoogleMap.OnMarkerClickListener {
    lateinit var binding: FragmentMapsBinding
    private val compositeDisposable = CompositeDisposable()
    private lateinit var placesClient: PlacesClient
//    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null
    private val defaultLocation = LatLng(43.2, 76.8)
    private val callback = OnMapReadyCallback {
        onMapReadyCallback(it)
    }

    @InjectPresenter
    lateinit var mapsPresenter: MapsPresenter

    @ProvidePresenter
    fun providePresenter() = MapsPresenter(
        LocationServices.getFusedLocationProviderClient(requireContext())
    )

    private fun onMapReadyCallback(googleMap: GoogleMap){
        mapsPresenter.map = googleMap

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
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
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
        mapsPresenter.map?.animateCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    marker.position.latitude,
                    marker.position.longitude
                )
            )
        )
        return true
    }

    override fun getLocationPermission() {
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

    override fun updateLocationUI() {
        mapsPresenter.updateLocationUI()
    }

    override fun getDeviceLocation() {
        mapsPresenter.getDeviceLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        mapsPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults)
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





    override fun generateRandomMarkers(googleMap: GoogleMap) {
        mapsPresenter.generateRandomMarkers(googleMap)
    }

    override fun generateRandomCoordinates(min: Int, max: Int, googleMap: GoogleMap) {
        mapsPresenter.generateRandomCoordinates(min, max, googleMap)
    }
    companion object {
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }
}