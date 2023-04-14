package com.example.findmyflavour.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Repositories.AddressRepo;

/**
 * Represents the ViewModel for the Address entity
 */
public class AddressViewModel extends AndroidViewModel {
    private AddressRepo repo;

    public AddressViewModel(@NonNull Application application) {
        super(application);
        repo = new AddressRepo(application);
    }

    public void insert(Address address) throws InterruptedException {
        repo.insert(address);
    }

    public void update(Address address) {
        repo.update(address);
    }

    public void delete(Address address) {
        repo.delete(address);
    }

    public Address findAddressById(int addressId) {
        return repo.findAddressById(addressId);
    }

    public Address findAddressByLatLong(String latitude, String longitude) { return repo.findAddressByLatLong(latitude, longitude);}

    public Address findLastInsertedAddress() {return repo.findLastInsertedAddress();}
}
