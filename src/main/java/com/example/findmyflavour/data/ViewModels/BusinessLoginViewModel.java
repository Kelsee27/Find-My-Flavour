package com.example.findmyflavour.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.findmyflavour.data.Models.BusinessLogin;
import com.example.findmyflavour.data.Repositories.BusinessLoginRepo;

/**
 * Represents the ViewModel for the BusinessLogin entity
 */
public class BusinessLoginViewModel extends AndroidViewModel {
    private BusinessLoginRepo repo;

    public BusinessLoginViewModel(@NonNull Application application) {
        super(application);
        repo = new BusinessLoginRepo(application);
    }

    public void insert(BusinessLogin businessLogin) {
        repo.insert(businessLogin);
    }

    public void update(BusinessLogin login) {
        repo.update(login);
    }

    public void delete(BusinessLogin login) {
        repo.delete(login);
    }

    public BusinessLogin getByLogin(String email, String password) {
        return repo.getBusinessLogin(email, password);
    }

    public BusinessLogin getByEmail(String email) {
        return repo.getByEmail(email);
    }
}
