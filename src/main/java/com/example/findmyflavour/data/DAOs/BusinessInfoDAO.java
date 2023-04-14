package com.example.findmyflavour.data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.findmyflavour.data.Models.BusinessInfo;

import java.util.List;

/**
 * Represents the data-access object for the BusinessInfo entity
 */
@Dao
public interface BusinessInfoDAO {
    @Update
    void update(BusinessInfo businessInfo);

    @Insert
    void insert(BusinessInfo businessInfo);

    @Delete
    void delete(BusinessInfo businessInfo);

    @Query("SELECT * FROM businessinfo")
    List<BusinessInfo> getAll();

    @Query("SELECT * FROM businessinfo WHERE businessInfoId=:businessInfoId LIMIT 1")
    BusinessInfo findBusinessById(final int businessInfoId);

    @Query("SELECT * FROM businessinfo WHERE business_login_id=:businessInfoOwnerId")
    List<BusinessInfo> findBusinessByOwnerId(final int businessInfoOwnerId);
}
