package com.example.findmyflavour;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Geocoder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Models.BusinessInfo;
import com.example.findmyflavour.data.Models.Hours;
import com.example.findmyflavour.data.ViewModels.AddressViewModel;
import com.example.findmyflavour.data.ViewModels.BusinessInfoViewModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Contains methods to populate the RecyclerView in the fragment_manage_business.xml layout.
 * This RecyclerView holds the information for each business that is owned by the business user who is currently logged in.
 * Each business’ information is populated into an individual CardView layout found in business_list_item.xml
 */
public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.BusinessViewHolder> {
    private HashMap<Integer, BusinessInfo> businessInfoHashMap;
    private HashMap<Integer, String> addressHashMap;
    private HashMap<Integer, Hours> hoursHashMap;

    public static class BusinessViewHolder extends RecyclerView.ViewHolder {
        TextView busName;
        TextView busDescription;
        TextView busAddress;
        TextView monOpen;
        TextView monClose;
        TextView tuesOpen;
        TextView tuesClose;
        TextView wedOpen;
        TextView wedClose;
        TextView thursOpen;
        TextView thursClose;
        TextView friOpen;
        TextView friClose;
        TextView satOpen;
        TextView satClose;
        TextView sunOpen;
        TextView sunClose;

        Button deleteBtn;
        Button editHoursBtn;
        Button editBusinessInfoBtn;

        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);
            this.busName = (TextView) itemView.findViewById(R.id.business_list_item_name);
            this.busDescription = (TextView) itemView.findViewById(R.id.business_list_item_desc);
            this.busAddress = (TextView) itemView.findViewById(R.id.business_list_item_address);
            this.monOpen = (TextView) itemView.findViewById(R.id.business_list_item_mon_open);
            this.monClose = (TextView) itemView.findViewById(R.id.business_list_item_mon_close);
            this.tuesOpen = (TextView) itemView.findViewById(R.id.business_list_item_tues_open);
            this.tuesClose = (TextView) itemView.findViewById(R.id.business_list_item_tues_close);
            this.wedOpen = (TextView) itemView.findViewById(R.id.business_list_item_wed_open);
            this.wedClose = (TextView) itemView.findViewById(R.id.business_list_item_wed_close);
            this.thursOpen = (TextView) itemView.findViewById(R.id.business_list_item_thurs_open);
            this.thursClose = (TextView) itemView.findViewById(R.id.business_list_item_thurs_close);
            this.friOpen = (TextView) itemView.findViewById(R.id.business_list_item_fri_open);
            this.friClose = (TextView) itemView.findViewById(R.id.business_list_item_fri_close);
            this.satOpen = (TextView) itemView.findViewById(R.id.business_list_item_sat_open);
            this.satClose = (TextView) itemView.findViewById(R.id.business_list_item_sat_close);
            this.sunOpen = (TextView) itemView.findViewById(R.id.business_list_item_sun_open);
            this.sunClose = (TextView) itemView.findViewById(R.id.business_list_item_sun_close);

            this.deleteBtn = (Button) itemView.findViewById(R.id.business_list_delete_btn);
            this.editHoursBtn = (Button) itemView.findViewById(R.id.business_list_edit_hours_btn);
            this.editBusinessInfoBtn = (Button) itemView.findViewById(R.id.business_list_edit_info_btn);
        }
    }

    BusinessInfoViewModel businessInfoViewModel;
    AddressViewModel addressViewModel;
    Context context;

    public BusinessListAdapter(BusinessInfoViewModel businessInfoViewModel,
                               AddressViewModel addressViewModel,
                               Context context,
                               HashMap<Integer, BusinessInfo> businessInfoHashMap,
                               HashMap<Integer, String> addressHashMap,
                               HashMap<Integer, Hours> hoursHashMap) {
        this.businessInfoViewModel = businessInfoViewModel;
        this.addressViewModel = addressViewModel;
        this.context = context;
        this.businessInfoHashMap = businessInfoHashMap;
        this.addressHashMap = addressHashMap;
        this.hoursHashMap = hoursHashMap;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_list_item, parent, false);

        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        TextView busName = holder.busName;
        TextView busDescription = holder.busDescription;
        TextView busAddress = holder.busAddress;
        TextView monOpen = holder.monOpen;
        TextView monClose = holder.monClose;
        TextView tuesOpen = holder.tuesOpen;
        TextView tuesClose = holder.tuesClose;
        TextView wedOpen = holder.wedOpen;
        TextView wedClose = holder.wedClose;
        TextView thursOpen = holder.thursOpen;
        TextView thursClose = holder.thursClose;
        TextView friOpen = holder.friOpen;
        TextView friClose = holder.friClose;
        TextView satOpen = holder.satOpen;
        TextView satClose = holder.satClose;
        TextView sunOpen = holder.sunOpen;
        TextView sunClose = holder.sunClose;

        BusinessInfo businessInfo = businessInfoHashMap.get(holder.getAdapterPosition());
        String address = addressHashMap.get(holder.getAdapterPosition());
        Hours hours = hoursHashMap.get(holder.getAdapterPosition());

        assert businessInfo != null;
        busName.setText(businessInfo.getBusinessName());
        busDescription.setText(businessInfo.getDescription());
        busAddress.setText(address);
        assert hours != null;
        monOpen.setText(hours.getMondayOpen());
        monClose.setText(hours.getMondayClose());
        tuesOpen.setText(hours.getTuesdayOpen());
        tuesClose.setText(hours.getTuesdayClose());
        wedOpen.setText(hours.getWednesdayOpen());
        wedClose.setText(hours.getWednesdayClose());
        thursOpen.setText(hours.getThursdayOpen());
        thursClose.setText(hours.getThursdayClose());
        friOpen.setText(hours.getFridayOpen());
        friClose.setText(hours.getFridayClose());
        satOpen.setText(hours.getSaturdayOpen());
        satClose.setText(hours.getSaturdayClose());
        sunOpen.setText(hours.getSundayOpen());
        sunClose.setText(hours.getSundayClose());

        // Deletes the selected business from the database and removes from the RecyclerView
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                businessInfoViewModel.delete(businessInfo);
                businessInfoHashMap.remove(holder.getAdapterPosition());
                hoursHashMap.remove(holder.getAdapterPosition());
                addressHashMap.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        // Allows for the user to edit their business description and address through a pop-up alert
        // then updates those models in the database
        holder.editBusinessInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating a layout to hold all the EditTexts
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                // Set an EditText view to get user inputDesc
                final EditText inputDesc = new EditText(context);
                inputDesc.setText(businessInfo.getDescription());

                //EditTexts for the address, the address will be updated in the database ONLY if all
                //these fields are filled
                final EditText inputStreetNum = new EditText(context);
                inputStreetNum.setHint("Street Number");
                final EditText inputStreetName = new EditText(context);
                inputStreetName.setHint("Street Name");
                final EditText inputCity = new EditText(context);
                inputCity.setHint("City");
                final EditText inputPostalCode = new EditText(context);
                inputPostalCode.setHint("Postal Code");

                //Add all TextViews to the layout
                layout.addView(inputDesc);
                layout.addView(inputStreetNum);
                layout.addView(inputStreetName);
                layout.addView(inputCity);
                layout.addView(inputPostalCode);

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Edit Business Info");

                alert.setView(layout);

                //Handles when the "Update" button is pressed
                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        businessInfo.setDescription(inputDesc.getText().toString());
                        businessInfoViewModel.update(businessInfo);

                        //Checking if all address related fields are filled, if so update the data model
                        // if not, do nothing
                        if (!TextUtils.isEmpty(inputStreetNum.getText().toString()) &&
                                !TextUtils.isEmpty(inputStreetName.getText().toString()) &&
                                !TextUtils.isEmpty(inputCity.getText().toString()) &&
                                !TextUtils.isEmpty(inputPostalCode.getText().toString())) {
                            Address addressModel = addressViewModel.findAddressById(businessInfo.getAddressId());
                            addressModel.setStreetNum(inputStreetNum.getText().toString());
                            addressModel.setStreet(inputStreetName.getText().toString());
                            addressModel.setCity(inputCity.getText().toString());
                            addressModel.setPostalCode(inputPostalCode.getText().toString());

                            String longLat = getLongLat(addressModel);
                            addressModel.setLatitude(longLat.split(" ", 2)[0]);
                            addressModel.setLongitude(longLat.split(" ", 2)[1]);

                            addressViewModel.update(addressModel);
                        }
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                });

                //Exits the pop-up when the "Cancel" button is pressed and does not modify any data
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                alert.show();
            }
        });

        //TODO: Complete "Edit Hours" functionality
        holder.editHoursBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return businessInfoHashMap.size();
    }

    /**
     * Finds the longitude and latitude of an address
     *
     * @return String
     */
    private String getLongLat(Address address) {
        Geocoder geocoder = new Geocoder(context);
        List<android.location.Address> addresses;

        try {
            addresses = geocoder.getFromLocationName(address.toString(), 1);

            if (addresses != null) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();

                return latitude + " " + longitude;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "50.6745 -120.3273";
    }
}