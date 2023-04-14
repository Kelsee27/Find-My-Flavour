package com.example.findmyflavour.data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.findmyflavour.data.Models.BusinessLogin;

/**
 * Represents the data-access object for the BusinessLogin entity
 */
@Dao
public interface BusinessLoginDAO {
    @Update
    void update(BusinessLogin... businessLogins);

    @Insert
    void insert(BusinessLogin businessLogin);

    @Delete
    void delete(BusinessLogin businessLogin);

    @Query("SELECT * FROM businesslogin WHERE email = :email AND " +
            "password = :password LIMIT 1")
    BusinessLogin getBusinessLogin(String email, String password);

    @Query("SELECT * FROM businesslogin WHERE email = :email LIMIT 1")
    BusinessLogin getByEmail(String email);
}
