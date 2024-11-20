package com.mapcompose

import android.os.Build
import android.view.Surface.CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS
import android.view.Surface.FRAME_RATE_COMPATIBILITY_FIXED_SOURCE
import android.view.SurfaceControl
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState


val styleJson = """
    [
    {
      featureType: "administrative",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#d6e2e6"
        }
      ]
    },
    {
      featureType: "administrative",
      elementType: "geometry.stroke",
      stylers: [
        {
          color: "#cfd4d5"
        }
      ]
    },
    {
      featureType: "administrative",
      elementType: "labels.text.fill",
      stylers: [
        {
          color: "#7492a8"
        }
      ]
    },
    {
      featureType: "administrative.neighborhood",
      elementType: "labels.text.fill",
      stylers: [
        {
          lightness: 25
        }
      ]
    },
    {
      featureType: "landscape.man_made",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#dde2e3"
        }
      ]
    },
    {
      featureType: "landscape.man_made",
      elementType: "geometry.stroke",
      stylers: [
        {
          color: "#cfd4d5"
        }
      ]
    },
    {
      featureType: "landscape.natural",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#dde2e3"
        }
      ]
    },
    {
      featureType: "landscape.natural",
      elementType: "labels.text.fill",
      stylers: [
        {
          color: "#7492a8"
        }
      ]
    },
    {
      featureType: "landscape.natural.terrain",
      elementType: "all",
      stylers: [
        {
          visibility: "off"
        }
      ]
    },
    {
      featureType: "poi.business",
      stylers: [
        {
          visibility: "off"
        }
      ]
    },
    {
      featureType: "poi",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#dde2e3"
        }
      ]
    },
    {
      featureType: "poi",
      elementType: "labels.text.fill",
      stylers: [
        {
          color: "#9FACAE"
        }
      ]
    },
    {
      featureType: "poi",
      elementType: "labels.icon",
      stylers: [
        {
          color: "#9FACAE"
        }
      ]
    },
    {
      featureType: "poi.park",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#a9de83"
        }
      ]
    },
    {
      featureType: "poi.park",
      elementType: "geometry.stroke",
      stylers: [
        {
          color: "#bae6a1"
        }
      ]
    },
    {
      featureType: "poi.sports_complex",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#c6e8b3"
        }
      ]
    },
    {
      featureType: "poi.sports_complex",
      elementType: "geometry.stroke",
      stylers: [
        {
          color: "#bae6a1"
        }
      ]
    },
    {
      featureType: "road",
      elementType: "labels.text.fill",
      stylers: [
        {
          color: "#000000"
        }
      ]
    },
    {
      featureType: "road",
      elementType: "labels.icon",
      stylers: [
        {
          saturation: -45
        },
        {
          visibility: "on"
        }
      ]
    },
    {
      featureType: "road.highway",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#c1d1d6"
        }
      ]
    },
    {
      featureType: "road.highway",
      elementType: "geometry.stroke",
      stylers: [
        {
          color: "#a6b5bb"
        }
      ]
    },
    {
      featureType: "road.highway",
      elementType: "labels.icon",
      stylers: [
        {
          visibility: "on"
        }
      ]
    },
    {
      featureType: "road.highway.controlled_access",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#9fb6bd"
        }
      ]
    },
    {
      featureType: "road.arterial",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#ffffff"
        }
      ]
    },
    {
      featureType: "road.local",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#ffffff"
        }
      ]
    },
    {
      featureType: "transit",
      elementType: "labels.icon",
      stylers: [
        {
          saturation: -70
        }
      ]
    },
    {
      featureType: "transit.line",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#b4cbd4"
        }
      ]
    },
    {
      featureType: "transit.line",
      elementType: "labels.text.fill",
      stylers: [
        {
          color: "#588ca4"
        }
      ]
    },
    {
      featureType: "transit.station",
      elementType: "all",
      stylers: [
        {
          visibility: "off"
        }
      ]
    },
    {
      featureType: "transit.station",
      elementType: "labels.text.fill",
      stylers: [
        {
          color: "#008cb5"
        },
        {
          visibility: "on"
        }
      ]
    },
    {
      featureType: "transit.station.airport",
      elementType: "geometry.fill",
      stylers: [
        {
          saturation: -100
        },
        {
          lightness: -5
        }
      ]
    },
    {
      featureType: "water",
      elementType: "geometry.fill",
      stylers: [
        {
          color: "#a6cbe3"
        }
      ]
    }
  ]
""".trimIndent()
@Composable
fun JetComposeGoogleMapView(){
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(LatLng(11.566536089516111, 104.92124231395368), 18f)
  }
  val uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = true))
  }
  val mapProperties by remember {
    mutableStateOf(MapProperties(mapStyleOptions = MapStyleOptions(styleJson)))
  }
  LaunchedEffect(key1 = Unit) {
  }

    GoogleMap(modifier = Modifier.fillMaxSize(),cameraPositionState=cameraPositionState, uiSettings = uiSettings, properties =mapProperties )
}


@Preview(showBackground = true)
@Composable
private fun JetComposeGoogleMapViewPreview() {
  JetComposeGoogleMapView()
}
