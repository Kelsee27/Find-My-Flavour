package com.example.findmyflavour.data.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Represents the BusinessLogin table for the database
 */
@Entity()
public class BusinessLogin {
    @PrimaryKey(autoGenerate = true)
    public int businessLoginId;

    @ColumnInfo(name = "owner_name")
    public String ownerName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    public int getBusinessLoginId() {
        return businessLoginId;
    }

    public void setBusinessLoginId(int businessLoginId) {
        this.businessLoginId = businessLoginId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
