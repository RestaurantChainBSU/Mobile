package com.example.mobile.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.R
import com.example.mobile.dishes.DishesActivity
import com.example.mobile.network.Apifactory
import com.example.mobile.order.OrderActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var gmap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        imageButton2.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        gmap!!.setMinZoomPreference(10.5f)
        val ny = LatLng(53.901028, 27.567705)
        gmap!!.moveCamera(CameraUpdateFactory.newLatLng(ny))

        loading.visibility = View.VISIBLE
        spin_kit.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            val restaraunts = Apifactory.api.getRestaraunts().await()
            withContext(Dispatchers.Main) {
                loading.visibility = View.INVISIBLE
                spin_kit.visibility = View.INVISIBLE
                for(item in restaraunts) {
                    gmap!!.addMarker(
                        MarkerOptions()
                            .position(LatLng(item.latitude, item.longitude))
                            .title("${item.id}")
                            .icon(
                                BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_RED)
                            )
                    )
                }
            }
        }

        gmap!!.setOnMarkerClickListener {
            val intent = Intent(this, DishesActivity::class.java)
            val b = Bundle()
            b.putString("key", it.title)

            intent.putExtras(b)

            startActivity(intent)

            true
        }
    }

    companion object {
        private const val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
}