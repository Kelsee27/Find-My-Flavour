package com.example.findmyflavour.data.Repositories;

import android.app.Application;

import com.example.findmyflavour.data.DAOs.HoursDAO;
import com.example.findmyflavour.data.Database.FindMyFlavourDatabase;
import com.example.findmyflavour.data.Models.Hours;

/**
 * Represents the repository for the Hours entity
 */
public class HoursRepo {

    private HoursDAO hoursDAO;
    private Hours hours;

    public HoursRepo(Application application) {
        FindMyFlavourDatabase database = FindMyFlavourDatabase.getInstance(application);
        hoursDAO = database.getHoursDAO();
    }

    public void insert(Hours hours) {
        hoursDAO.insert(hours);
    }

    public void update(Hours hours) {
        hoursDAO.update(hours);
    }

    public void delete(Hours hours) {
        hoursDAO.delete(hours);
    }

    public Hours findHoursById(int hoursId) {
        hours = hoursDAO.findHoursById(hoursId);
        return hours;
    }

    public Hours findLastInsertedHours() {
        hours = hoursDAO.findLastInsertedHours();
        return hours;
    }
}
