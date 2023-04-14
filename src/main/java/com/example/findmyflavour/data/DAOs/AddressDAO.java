package com.example.findmyflavour.data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.findmyflavour.data.Models.Address;

/**
 * Represents the data-access object for the Address entity
 */
@Dao
public interface AddressDAO {
    @Update
    void update(Address address);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Address address);

    @Delete
    void delete(Address address);

    @Query("SELECT * FROM address WHERE addressId=:addressId LIMIT 1")
    Address findAddressById(final int addressId);

    @Query("SELECT * FROM address WHERE latitude=:latitude AND longitude=:longitude LIMIT 1")
    Address findAddressByLatLong(String latitude, String longitude);

    @Query("SELECT * FROM address ORDER BY addressId DESC LIMIT 1")
    Address findLastInsertedAddress();
}
