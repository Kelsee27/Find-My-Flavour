package com.example.findmyflavour.data.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Represents the Address table for the database
 */
@Entity
public class Address implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int addressId;

    @ColumnInfo(name = "longitude")
    public String longitude;

    @ColumnInfo(name = "latitude")
    public String latitude;

    @ColumnInfo(name = "street_num")
    public String streetNum;

    @ColumnInfo(name = "street")
    public String street;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "postal_code")
    public String postalCode;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return streetNum + ' ' + street + ", " + city + ", " + postalCode;
    }
}
