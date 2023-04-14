package com.example.findmyflavour.data.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.findmyflavour.data.DAOs.AddressDAO;
import com.example.findmyflavour.data.DAOs.BusinessInfoDAO;
import com.example.findmyflavour.data.DAOs.BusinessLoginDAO;
import com.example.findmyflavour.data.DAOs.HoursDAO;
import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Models.BusinessInfo;
import com.example.findmyflavour.data.Models.BusinessLogin;
import com.example.findmyflavour.data.Models.Hours;

/**
 * Creates and maintains the local Room database for the application.
 * Includes the BusinessLogin, BusinessInfo, Address, and Hours entities.
 */
@Database(entities = {BusinessLogin.class, BusinessInfo.class, Address.class, Hours.class}, version = 1)
public abstract class FindMyFlavourDatabase extends RoomDatabase {

    public abstract BusinessLoginDAO getBusinessLoginDAO();

    public abstract BusinessInfoDAO getBusinessInfoDAO();

    public abstract AddressDAO getAddressDAO();

    public abstract HoursDAO getHoursDAO();

    private static final String DB_NAME = "findMyFlavourDatabase.db";
    private static FindMyFlavourDatabase instance;

    public static synchronized FindMyFlavourDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                            FindMyFlavourDatabase.class,
                            DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

