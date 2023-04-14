package com.example.findmyflavour.data.Repositories;

import android.app.Application;

import com.example.findmyflavour.data.DAOs.BusinessLoginDAO;
import com.example.findmyflavour.data.Database.FindMyFlavourDatabase;
import com.example.findmyflavour.data.Models.BusinessLogin;

/**
 * Represents the repository for the BusinessLogin entity
 */
public class BusinessLoginRepo {
    private BusinessLogin businessLogin;
    private BusinessLoginDAO businessLoginDAO;

    public BusinessLoginRepo(Application application) {
        FindMyFlavourDatabase database = FindMyFlavourDatabase.getInstance(application);
        businessLoginDAO = database.getBusinessLoginDAO();
    }

    public void insert(BusinessLogin businessLogin) {
        businessLoginDAO.insert(businessLogin);
    }

    public void update(BusinessLogin businessLogin) {
        businessLoginDAO.update(businessLogin);
    }

    public void delete(BusinessLogin businessLogin) {
        businessLoginDAO.delete(businessLogin);
    }

    public BusinessLogin getBusinessLogin(String email, String password) {
        businessLogin = businessLoginDAO.getBusinessLogin(email, password);
        return businessLogin;
    }

    public BusinessLogin getByEmail(String email) {
        businessLogin = businessLoginDAO.getByEmail(email);
        return businessLogin;
    }
}
