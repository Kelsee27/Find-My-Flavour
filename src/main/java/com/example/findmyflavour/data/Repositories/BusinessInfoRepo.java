package com.example.findmyflavour.data.Repositories;

import android.app.Application;

import com.example.findmyflavour.data.DAOs.BusinessInfoDAO;
import com.example.findmyflavour.data.Database.FindMyFlavourDatabase;
import com.example.findmyflavour.data.Models.BusinessInfo;

import java.util.List;

/**
 * Represents the repository for the BusinessInfo entity
 */
public class BusinessInfoRepo {
    private BusinessInfoDAO businessInfoDAO;

    public BusinessInfoRepo(Application application) {
        FindMyFlavourDatabase database = FindMyFlavourDatabase.getInstance(application);
        businessInfoDAO = database.getBusinessInfoDAO();
    }

    public void insert(BusinessInfo businessInfo) {
        businessInfoDAO.insert(businessInfo);
    }

    public void update(BusinessInfo businessInfo) {
        businessInfoDAO.update(businessInfo);
    }

    public void delete(BusinessInfo businessInfo) {
        businessInfoDAO.delete(businessInfo);
    }

    public BusinessInfo findBusinessById(int businessId) {
        return businessInfoDAO.findBusinessById(businessId);
    }

    public List<BusinessInfo> getAllBusinessInfo() {
        return businessInfoDAO.getAll();
    }

    public List<BusinessInfo> findBusinessByOwnerId(final int businessOwnerId) {
        return businessInfoDAO.findBusinessByOwnerId(businessOwnerId);
    }
}
