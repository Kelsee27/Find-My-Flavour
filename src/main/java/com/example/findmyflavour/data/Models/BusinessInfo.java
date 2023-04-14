package com.example.findmyflavour.data.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Represents the BusinessInfo table for the database.
 * Contains foreign keys to the Address, Hours, and BusinessLogin classes.
 */
@Entity(foreignKeys = {@ForeignKey(entity = Address.class,
        parentColumns = "addressId",
        childColumns = "address_id",
        onDelete = CASCADE),
        @ForeignKey(entity = Hours.class,
        parentColumns = "hoursId",
        childColumns = "hours_id",
        onDelete = CASCADE),
        @ForeignKey(entity = BusinessLogin.class,
        parentColumns = "businessLoginId",
        childColumns = "business_login_id")},
        indices = {@Index(value = {"address_id"}, unique = true),
        @Index(value = {"hours_id"}, unique = true)})
public class BusinessInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int businessInfoId;

    @ColumnInfo(name = "business_name")
    public String businessName;

    @ColumnInfo(name = "address_id")
    public int addressId;

    @ColumnInfo(name = "hours_id")
    public int hoursId;

    @ColumnInfo(name = "business_login_id")
    public int businessLoginId;

    @ColumnInfo(name = "description")
    public String description;

    public int getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(int businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getHoursId() {
        return hoursId;
    }

    public void setHoursId(int hoursId) {
        this.hoursId = hoursId;
    }

    public int getBusinessLoginId() {
        return businessLoginId;
    }

    public void setBusinessLoginId(int businessLoginId) {
        this.businessLoginId = businessLoginId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
