package com.dessy.screeningtest.ui.event

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dessy.screeningtest.databinding.FragmentMapsBinding
import com.dessy.screeningtest.model.EventEntity
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay.*


class MapsFragment : Fragment(), EvenAdapterClickListener {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var viewModel: EventViewModel
    private lateinit var adapter: HorizontalEventAdapter

    private lateinit var map: MapView
    private lateinit var mapController: IMapController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider
            .NewInstanceFactory())
            .get(EventViewModel::class.java)

        adapter = HorizontalEventAdapter(viewModel.getEvents(), this)
        with(binding) {
            rvEvent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvEvent.adapter = adapter
        }

        Configuration.getInstance().userAgentValue = requireActivity().packageName

        map = binding.mapView
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setBuiltInZoomControls(true)
        map.setMultiTouchControls(true)
        mapController = map.controller
        mapController.setZoom(12.5)
        val startPoint = GeoPoint(-6.2, 106.816)
        mapController.setCenter(startPoint)

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted")
            } else {
                Log.v("TAG", "Permission is revoked")
                requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
        }
    }

    override fun onItemClickListener(item: EventEntity) {
        val point = GeoPoint(item.lat, item.long)
        mapController.setCenter(point)

        val marker = org.osmdroid.views.overlay.Marker(map)
        marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM)
        marker.textLabelBackgroundColor = backgroundColor
        marker.textLabelFontSize = 12
        marker.textLabelForegroundColor = fontColor
        marker.title = item.event

        marker.icon = null
        marker.position = point
        map.overlays.add(marker)
        mapController.setZoom(15.5)
    }
}