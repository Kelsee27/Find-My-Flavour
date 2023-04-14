package com.example.findmyflavour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Models.BusinessInfo;
import com.example.findmyflavour.data.ViewModels.AddressViewModel;
import com.example.findmyflavour.data.ViewModels.BusinessInfoViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Contains methods that control the Google Maps fragment including populating the map with business markers.
 * This class is used in conjunction with the fragment_maps.xml layout.
 */
public class MapsFragment extends Fragment {
    private BusinessInfoViewModel businessInfoViewModel;
    private AddressViewModel addressViewModel;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * The default starting view of the map is Kamloops
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            LatLng kamloops = new LatLng(50.6745, -120.3273);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kamloops, 9));

            List<BusinessInfo> businessInfoList = businessInfoViewModel.getAllBusinessInfo();
            //Loop through all businesses and add a marker for each of them including their name and
            // description
            for (BusinessInfo businessInfo : businessInfoList) {
                Address address = addressViewModel.findAddressById(businessInfo.getAddressId());
                googleMap.addMarker(new MarkerOptions()
                        .title(businessInfo.getBusinessName())
                        .snippet(businessInfo.getDescription())
                        .position(new LatLng(Double.parseDouble(address.getLatitude()),
                                Double.parseDouble(address.getLongitude()))));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        businessInfoViewModel = new ViewModelProvider(this).get(BusinessInfoViewModel.class);
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}