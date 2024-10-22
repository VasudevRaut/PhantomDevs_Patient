package com.example.pccoe_oct_2024_hack.LocationHelper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetLocation {

    public Context context;
    List<Double> location;
    public GetLocation(Context context){
        this.context = context;
        location = new ArrayList<>();
    }

    public List<Double> getCurrentLocation() {
//        Toast.makeText(context, "getcurrentLocation", Toast.LENGTH_SHORT).show();
        LocationRequest locationRequest = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            locationRequest = new LocationRequest();
        }
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return new ArrayList<>();
        }


        LocationServices.getFusedLocationProviderClient(context)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(context)
                                .removeLocationUpdates(this);
//                        Toast.makeText(context, "when set locatin", Toast.LENGTH_SHORT).show();
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocIndex = locationResult.getLocations().size() - 1;
                            double lati = locationResult.getLocations().get(latestlocIndex).getLatitude();
                            double longi = locationResult.getLocations().get(latestlocIndex).getLongitude();
//                            textLatLong.setText(String.format("Latitude : %s\n Longitude: %s", lati, longi));
                            location.add(lati);
                            location.add(longi);
//                            Location location = new Location("providerNA");
//                            location.setLongitude(longi);
//                            location.setLatitude(lati);
//                            fetchaddressfromlocation(location);

                        } else {

                        }
                    }
                }, Looper.getMainLooper());
        return location;
    }


    public static String getAddress(double docLat, double docLang,Context context) {
        String address = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(docLat, docLang, 1); // Get one address
            if (addresses != null && !addresses.isEmpty()) {
                Address addressObj = addresses.get(0);
                // Construct the address string (you can customize this as needed)
                address = addressObj.getAddressLine(0); // Get the full address line
            }
        } catch (IOException e) {
            e.printStackTrace();
            address = "Unable to get address";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            address = "Invalid latitude or longitude";
        }

        return address;
    }


}
