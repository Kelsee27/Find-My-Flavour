package com.example.findmyflavour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Models.BusinessInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods that allows a business user to add details of their business including:
 * business name, business description, and business address. Once all validations are fulfilled,
 * user can move to the Add Hours activity.
 * Used for the activity_add_business.xml layout.
 */
public class AddBusiness extends AppCompatActivity {

    EditText businessName;
    EditText businessDesc;
    EditText businessStreetNum;
    EditText businessStreet;
    EditText businessCity;
    EditText businessPostalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        businessName = findViewById(R.id.enter_business_name);
        businessDesc = findViewById(R.id.enter_business_description);
        businessStreetNum = findViewById(R.id.enter_business_streetNum);
        businessStreet = findViewById(R.id.enter_business_street);
        businessCity = findViewById(R.id.enter_business_city);
        businessPostalCode = findViewById(R.id.enter_business_postal_code);
    }

    /**
     * Gathers the user's Business Info and Address they have provided then sends those objects to
     * the hours activity
     *
     * @param view
     */
    public void insertNewBusiness(View view) {
        //Ensures all fields are completed
        if (!checkAllFieldsAreFilled()) {
            return;
        }
        Address address = new Address();
        //Get the address details from the EditTexts
        address.setStreetNum(businessStreetNum.getText().toString());
        address.setStreet(businessStreet.getText().toString());
        address.setCity(businessCity.getText().toString());
        address.setPostalCode(businessPostalCode.getText().toString());

        //Find the longitude and latitude from the entered address
        String longLat = getLongLat(address);
        address.setLatitude(longLat.split(" ", 2)[0]);
        address.setLongitude(longLat.split(" ", 2)[1]);

        //Get the business name and description from the EditTexts
        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setBusinessName(businessName.getText().toString());
        businessInfo.setDescription(businessDesc.getText().toString());

        goToHoursActivity(address, businessInfo);
    }

    /**
     * Validates that all the edit text fields are filled in before registering the business
     *
     * @return boolean
     */
    public boolean checkAllFieldsAreFilled() {
        boolean result = true;

        //Creates an ArrayList adding each field to it
        ArrayList<EditText> editTextList = new ArrayList<EditText>();
        editTextList.add(businessName);
        editTextList.add(businessDesc);
        editTextList.add(businessStreetNum);
        editTextList.add(businessStreet);
        editTextList.add(businessCity);
        editTextList.add(businessPostalCode);

        //Loops through each field to ensure they are all filled, if not set an error on the TextView
        for (EditText editText : editTextList) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                editText.setError("This field is required");
                result = false;
            }
        }
        return result;
    }

    /**
     * Finds the longitude and latitude of an address using Geocoder. If one cannot be found, return
     * Kamloops coordinates
     *
     * @param address
     * @return String
     */
    private String getLongLat(Address address) {
        Geocoder geocoder = new Geocoder(this);
        List<android.location.Address> addresses;

        try {
            addresses = geocoder.getFromLocationName(address.toString(), 1);

            if (addresses != null) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();

                return latitude + " " + longitude;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "50.6745 -120.3273";
    }

    /**
     * Sends the user's address and businessInfo to AddHours.java and starts the AddHours activity
     *
     * @param address
     * @param businessInfo
     */
    private void goToHoursActivity(Address address, BusinessInfo businessInfo) {
        Intent intent = new Intent(this, AddHours.class);
        intent.putExtra("ADDRESS", address);
        intent.putExtra("BUSINESS_INFO", businessInfo);
        startActivity(intent);
    }
}