package com.agh.faustyna.mobile;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android2ee.formation.librairies.google.map.utils.direction.DCACallBack;
import com.android2ee.formation.librairies.google.map.utils.direction.GDirectionsApiUtils;
import com.android2ee.formation.librairies.google.map.utils.direction.model.GDirection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Dojazd extends FragmentActivity implements OnMapReadyCallback, DCACallBack {

    private GoogleMap mMap;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLocationManager = (LocationManager)getSystemService(getApplicationContext().LOCATION_SERVICE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sanktuarium and move the camera
        LatLng sanktuarium = new LatLng(50.0201514,19.9353783);
        //LatLng devicePos = new LatLng(50.0204177, 19.9379689);
        Location myLocation = getLastKnownLocation();
        if (myLocation == null) {
            mMap.addMarker(new MarkerOptions().position(sanktuarium));
        } else {
            LatLng devicePos = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
            GDirectionsApiUtils.getDirection(this, devicePos, sanktuarium, GDirectionsApiUtils.MODE_DRIVING);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanktuarium));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

    @Override
    public void onDirectionLoaded(List<GDirection> directions) {
        for(GDirection direction:directions) {
            Log.e("MainActivity", "onDirectionLoaded : Draw GDirections Called with path " + directions);
            GDirectionsApiUtils.drawGDirection(direction, mMap);
        }
    }

    private Location getLastKnownLocation() {
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }
}
