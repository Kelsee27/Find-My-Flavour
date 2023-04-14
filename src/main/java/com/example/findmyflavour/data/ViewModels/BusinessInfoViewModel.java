package com.example.findmyflavour.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.findmyflavour.data.Models.BusinessInfo;
import com.example.findmyflavour.data.Repositories.BusinessInfoRepo;

import java.util.List;

/**
 * Represents the ViewModel for the BusinessInfo entity
 */
public class BusinessInfoViewModel extends AndroidViewModel {
    private BusinessInfoRepo repo;

    public BusinessInfoViewModel(@NonNull Application application) {
        super(application);
        repo = new BusinessInfoRepo(application);
    }

    public void insert(BusinessInfo businessInfo) {
        repo.insert(businessInfo);
    }

    public void update(BusinessInfo businessInfo) {
        repo.update(businessInfo);
    }

    public void delete(BusinessInfo businessInfo) {
        repo.delete(businessInfo);
    }

    public BusinessInfo findBusinessById(int businessId) {
        return repo.findBusinessById(businessId);
    }

    public List<BusinessInfo> getAllBusinessInfo() {
        return repo.getAllBusinessInfo();
    }

    public List<BusinessInfo> findBusinessByOwnerId(int businessId) {
        return repo.findBusinessByOwnerId(businessId);
    }
}
