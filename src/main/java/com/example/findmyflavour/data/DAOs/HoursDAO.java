package com.example.findmyflavour.data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.findmyflavour.data.Models.Hours;

/**
 * Represents the data-access object for the Hours entity
 */
@Dao
public interface HoursDAO {
    @Update
    void update(Hours hours);

    @Insert
    void insert(Hours hours);

    @Delete
    void delete(Hours hours);

    @Query("SELECT * FROM hours WHERE hoursId=:hoursId LIMIT 1")
    Hours findHoursById(final int hoursId);

    @Query("SELECT * FROM hours ORDER BY hoursId DESC LIMIT 1")
    Hours findLastInsertedHours();
}
