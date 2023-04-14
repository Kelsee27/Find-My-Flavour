package com.example.findmyflavour.data.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Represents the Hours table for the database
 */
@Entity
public class Hours implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int hoursId;

    @ColumnInfo(name = "monday_open")
    public String mondayOpen;

    @ColumnInfo(name = "tuesday_open")
    public String tuesdayOpen;

    @ColumnInfo(name = "wednesday_open")
    public String wednesdayOpen;

    @ColumnInfo(name = "thursday_open")
    public String thursdayOpen;

    @ColumnInfo(name = "friday_open")
    public String fridayOpen;

    @ColumnInfo(name = "saturday_open")
    public String saturdayOpen;

    @ColumnInfo(name = "sunday_open")
    public String sundayOpen;

    @ColumnInfo(name = "monday_close")
    public String mondayClose;

    @ColumnInfo(name = "tuesday_close")
    public String tuesdayClose;

    @ColumnInfo(name = "wednesday_close")
    public String wednesdayClose;

    @ColumnInfo(name = "thursday_close")
    public String thursdayClose;

    @ColumnInfo(name = "friday_close")
    public String fridayClose;

    @ColumnInfo(name = "saturday_close")
    public String saturdayClose;

    @ColumnInfo(name = "sunday_close")
    public String sundayClose;

    /**
     * Constructor that sets default value of all hours to closed
     */
    public Hours() {
        String defaultValue = "Closed";
        this.mondayOpen = defaultValue;
        this.mondayClose = defaultValue;
        this.tuesdayOpen = defaultValue;
        this.tuesdayClose = defaultValue;
        this.wednesdayOpen = defaultValue;
        this.wednesdayClose = defaultValue;
        this.thursdayOpen = defaultValue;
        this.thursdayClose = defaultValue;
        this.fridayOpen = defaultValue;
        this.fridayClose = defaultValue;
        this.saturdayOpen = defaultValue;
        this.saturdayClose = defaultValue;
        this.sundayOpen = defaultValue;
        this.sundayClose = defaultValue;
    }

    public int getHoursId() {
        return hoursId;
    }

    public void setHoursId(int hoursId) {
        this.hoursId = hoursId;
    }

    public String getMondayOpen() {
        return mondayOpen;
    }

    public void setMondayOpen(String mondayOpen) {
        this.mondayOpen = mondayOpen;
    }

    public String getTuesdayOpen() {
        return tuesdayOpen;
    }

    public void setTuesdayOpen(String tuesdayOpen) {
        this.tuesdayOpen = tuesdayOpen;
    }

    public String getWednesdayOpen() {
        return wednesdayOpen;
    }

    public void setWednesdayOpen(String wednesdayOpen) {
        this.wednesdayOpen = wednesdayOpen;
    }

    public String getThursdayOpen() {
        return thursdayOpen;
    }

    public void setThursdayOpen(String thursdayOpen) {
        this.thursdayOpen = thursdayOpen;
    }

    public String getFridayOpen() {
        return fridayOpen;
    }

    public void setFridayOpen(String fridayOpen) {
        this.fridayOpen = fridayOpen;
    }

    public String getSaturdayOpen() {
        return saturdayOpen;
    }

    public void setSaturdayOpen(String saturdayOpen) {
        this.saturdayOpen = saturdayOpen;
    }

    public String getSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(String sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getMondayClose() {
        return mondayClose;
    }

    public void setMondayClose(String mondayClose) {
        this.mondayClose = mondayClose;
    }

    public String getTuesdayClose() {
        return tuesdayClose;
    }

    public void setTuesdayClose(String tuesdayClose) {
        this.tuesdayClose = tuesdayClose;
    }

    public String getWednesdayClose() {
        return wednesdayClose;
    }

    public void setWednesdayClose(String wednesdayClose) {
        this.wednesdayClose = wednesdayClose;
    }

    public String getThursdayClose() {
        return thursdayClose;
    }

    public void setThursdayClose(String thursdayClose) {
        this.thursdayClose = thursdayClose;
    }

    public String getFridayClose() {
        return fridayClose;
    }

    public void setFridayClose(String fridayClose) {
        this.fridayClose = fridayClose;
    }

    public String getSaturdayClose() {
        return saturdayClose;
    }

    public void setSaturdayClose(String saturdayClose) {
        this.saturdayClose = saturdayClose;
    }

    public String getSundayClose() {
        return sundayClose;
    }

    public void setSundayClose(String sundayClose) {
        this.sundayClose = sundayClose;
    }
}
