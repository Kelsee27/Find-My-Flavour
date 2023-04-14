package com.example.findmyflavour.data.Repositories;

import android.app.Application;

import com.example.findmyflavour.data.DAOs.AddressDAO;
import com.example.findmyflavour.data.Database.FindMyFlavourDatabase;
import com.example.findmyflavour.data.Models.Address;

/**
 * Represents the repository for the Address entity
 */
public class AddressRepo {

    private AddressDAO addressDAO;
    private Address address;

    public AddressRepo(Application application) {
        FindMyFlavourDatabase database = FindMyFlavourDatabase.getInstance(application);
        addressDAO = database.getAddressDAO();
    }

    public void insert(Address address) throws InterruptedException {
        addressDAO.insert(address);
    }

    public void update(Address address) {
        addressDAO.update(address);
    }

    public void delete(Address address) {
        addressDAO.delete(address);
    }

    public Address findAddressById(int addressId) {
        address = addressDAO.findAddressById(addressId);
        return address;
    }

    public Address findAddressByLatLong(String latitude, String longitude) {
        address = addressDAO.findAddressByLatLong(latitude, longitude);
        return address;
    }

    public Address findLastInsertedAddress() {
        address = addressDAO.findLastInsertedAddress();
        return address;
    }
}
