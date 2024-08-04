package com.example.mymapview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    View view;
    private final int FINE_PERMISSION_CODE = 1;
    /*Location currentLocation;*/
    FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap myMap;
    private SearchView searchView;
    String  location;
    List<Address> addressList = null;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        context = requireContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);

        searchView = view.findViewById(R.id.mapSearch);
        CustomizeSearchView.customizeSearchView(searchView, context.getColor(R.color.black), context.getColor(R.color.black));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
        getLastLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                location = searchView.getQuery().toString();

                Geocoder geocoder = new Geocoder(context);


                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (addressList != null && !addressList.isEmpty()) {
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    myMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                } else {
                    Toast.makeText(getContext(), "Location not found", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
        getLastLocation();

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }


    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE_PERMISSION_CODE);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap= googleMap;
        myMap.getUiSettings().setZoomControlsEnabled(true);
        myMap.getUiSettings().setCompassEnabled(true);
        myMap.getUiSettings().setZoomGesturesEnabled(true);
        myMap.getUiSettings().setScrollGesturesEnabled(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.map_menu, menu);  // Inflate your menu resource
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mapNone) {
            Toast.makeText(getContext(), getString(R.string.none), Toast.LENGTH_SHORT).show();
            myMap.setMapType(GoogleMap.MAP_TYPE_NONE);
            return true;
        } else if (id == R.id.mapNormal) {
            Toast.makeText(getContext(), getString(R.string.normal), Toast.LENGTH_SHORT).show();
            myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            return true;
        } else if (id == R.id.mapSatellite){
            Toast.makeText(getContext(), getString(R.string.satellite), Toast.LENGTH_SHORT).show();
            myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return true;
        } else if (id == R.id.mapHybrid) {
            Toast.makeText(getContext(), getString(R.string.hybrid), Toast.LENGTH_SHORT).show();
            myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            return true;
        } else if (id == R.id.mapTerrain) {
            Toast.makeText(getContext(), getString(R.string.terrain), Toast.LENGTH_SHORT).show();
            myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(requireActivity(), "Location permission denied, please allow the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

}