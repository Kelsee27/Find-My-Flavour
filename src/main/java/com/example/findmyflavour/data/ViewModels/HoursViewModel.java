package com.example.findmyflavour.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.findmyflavour.data.Models.Hours;
import com.example.findmyflavour.data.Repositories.HoursRepo;

/**
 * Represents the ViewModel for the Hours entity
 */
public class HoursViewModel extends AndroidViewModel {
    HoursRepo repo;
    public HoursViewModel(@NonNull Application application) {
        super(application);
        repo = new HoursRepo(application);
    }

    public void insert(Hours hours) {
        repo.insert(hours);
    }

    public void update(Hours hours) {
        repo.update(hours);
    }

    public void delete(Hours hours) {
        repo.delete(hours);
    }

    public Hours findHoursById(int hoursId) {
        return repo.findHoursById(hoursId);
    }

    public Hours findLastInsertedHours() {return repo.findLastInsertedHours();}
}
