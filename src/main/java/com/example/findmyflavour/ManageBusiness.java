package com.example.findmyflavour;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Models.BusinessInfo;
import com.example.findmyflavour.data.Models.Hours;
import com.example.findmyflavour.data.ViewModels.AddressViewModel;
import com.example.findmyflavour.data.ViewModels.BusinessInfoViewModel;
import com.example.findmyflavour.data.ViewModels.HoursViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

/**
 * Contains methods that allow a business user to manage their current businesses or start the process of adding a new business.
 * This class is used in conjunction with the fragment_manage_business.xml layout.
 */
public class ManageBusiness extends Fragment implements View.OnClickListener {
    public static BusinessInfoViewModel businessInfoViewModel;
    private AddressViewModel addressViewModel;
    private HoursViewModel hoursViewModel;

    private static BusinessListAdapter adapter;
    private static RecyclerView recyclerView;
    private static HashMap<Integer, BusinessInfo> businessInfoHashMap;
    private static HashMap<Integer, String> addressHashMap;
    private static HashMap<Integer, Hours> hoursHashMap;

    public ManageBusiness() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_business, container, false);

        businessInfoViewModel = new ViewModelProvider(this).get(BusinessInfoViewModel.class);
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        hoursViewModel = new ViewModelProvider(this).get(HoursViewModel.class);

        businessInfoHashMap = new HashMap<>();
        addressHashMap = new HashMap<>();
        hoursHashMap = new HashMap<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.manage_business_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getAllUsersBusinesses();

        adapter = new BusinessListAdapter(businessInfoViewModel, addressViewModel, getContext(), businessInfoHashMap, addressHashMap, hoursHashMap);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.manage_business_add_fab);
        fab.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    /**
     * Find all businesses owned by the currently logged in user and grab each business's info
     */
    public void getAllUsersBusinesses() {
        List<BusinessInfo> businessInfoList = businessInfoViewModel.findBusinessByOwnerId(BusinessLoginFragment.getBusinessLoginId());

        //Check if the current user has one or more registered businesses
        if (businessInfoList != null) {
            int i = 0;
            //Loop through each business to grab its info
            for (BusinessInfo businessInfo : businessInfoList) {
                businessInfoHashMap.put(i, businessInfo);

                int addressId = businessInfo.getAddressId();
                Address address = addressViewModel.findAddressById(addressId);
                String stringAddress = address.getStreetNum() + " " + address.getStreet() + ", " + address.getCity();
                addressHashMap.put(i, stringAddress);

                int hoursId = businessInfo.getHoursId();
                Hours hours = hoursViewModel.findHoursById(hoursId);
                hoursHashMap.put(i, hours);

                i++;
            }
        }
    }

    /**
     * OnClick method for the AddBusiness FAB
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Moves to AddBusiness Activity when fab is clicked
            case R.id.manage_business_add_fab:
                Intent intent = new Intent(getContext(), AddBusiness.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}