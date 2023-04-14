package com.example.findmyflavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.findmyflavour.data.Models.Address;
import com.example.findmyflavour.data.Models.BusinessInfo;
import com.example.findmyflavour.data.Models.Hours;
import com.example.findmyflavour.data.ViewModels.AddressViewModel;
import com.example.findmyflavour.data.ViewModels.BusinessInfoViewModel;
import com.example.findmyflavour.data.ViewModels.HoursViewModel;

import java.util.Locale;

/**
 * Contains methods that allow a business user to add hours of operation to their business.
 * This class is used in conjunction with the activity_add_hours.xml layout.
 */
public class AddHours extends AppCompatActivity {

    private BusinessInfoViewModel businessInfoViewModel;
    private AddressViewModel addressViewModel;
    private HoursViewModel hoursViewModel;

    TextView textView;
    Button button;
    int hour, minute;
    String day, time;
    BusinessInfo businessInfo;
    Address address;
    Hours hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hours);
        hours = new Hours();

        businessInfoViewModel = new ViewModelProvider(this).get(BusinessInfoViewModel.class);
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        hoursViewModel = new ViewModelProvider(this).get(HoursViewModel.class);

        //Get the businessInfo and address objects from the AddBusiness Activity
        Intent intent = getIntent();
        businessInfo = (BusinessInfo) intent.getSerializableExtra("BUSINESS_INFO");
        address = (Address) intent.getSerializableExtra("ADDRESS");
    }

    /**
     * Finds which add hours button was pressed and sets the day and time to the appropriate one
     *
     * @param view
     */
    public void setTime(View view) {
        //Find which "Set Hours" button was pressed
        switch (view.getId()) {
            case R.id.add_hours_btn_open_mon:
                textView = findViewById(R.id.add_hours_textview_open_mon);
                day = "Mon";
                time = "open";
                break;
            case R.id.add_hours_btn_close_mon:
                textView = findViewById(R.id.add_hours_textview_close_mon);
                day = "Mon";
                time = "close";
                break;
            case R.id.add_hours_btn_open_tues:
                textView = findViewById(R.id.add_hours_textview_open_tues);
                day = "Tues";
                time = "open";
                break;
            case R.id.add_hours_btn_close_tues:
                textView = findViewById(R.id.add_hours_textview_close_tues);
                day = "Tues";
                time = "close";
                break;
            case R.id.add_hours_btn_open_wed:
                textView = findViewById(R.id.add_hours_textview_open_wed);
                day = "Wed";
                time = "open";
                break;
            case R.id.add_hours_btn_close_wed:
                textView = findViewById(R.id.add_hours_textview_close_wed);
                day = "Wed";
                time = "close";
                break;
            case R.id.add_hours_btn_open_thurs:
                textView = findViewById(R.id.add_hours_textview_open_thurs);
                day = "Thurs";
                time = "open";
                break;
            case R.id.add_hours_btn_close_thurs:
                textView = findViewById(R.id.add_hours_textview_close_thurs);
                day = "Thurs";
                time = "close";
                break;
            case R.id.add_hours_btn_open_fri:
                textView = findViewById(R.id.add_hours_textview_open_fri);
                day = "Fri";
                time = "open";
                break;
            case R.id.add_hours_btn_close_fri:
                textView = findViewById(R.id.add_hours_textview_close_fri);
                day = "Fri";
                time = "close";
                break;
            case R.id.add_hours_btn_open_sat:
                textView = findViewById(R.id.add_hours_textview_open_sat);
                day = "Sat";
                time = "open";
                break;
            case R.id.add_hours_btn_close_sat:
                textView = findViewById(R.id.add_hours_textview_close_sat);
                day = "Sat";
                time = "close";
                break;
            case R.id.add_hours_btn_open_sun:
                textView = findViewById(R.id.add_hours_textview_open_sun);
                day = "Sun";
                time = "open";
                break;
            case R.id.add_hours_btn_close_sun:
                textView = findViewById(R.id.add_hours_textview_close_sun);
                day = "Sun";
                time = "close";
                break;
            default:
                return;
        }
        //Calls the time picker pop up
        popTimePicker();
    }

    /**
     * Instantiates the Time Picker pop up and changes the appropriate TextView to the user's
     * selected time
     */
    public void popTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                String currentText = textView.getText().toString();
                currentText = currentText.split(" ", 2)[0];
                textView.setText(String.format(Locale.getDefault(), "%s %02d:%02d", currentText, hour, minute));
                //Populates the hours object with the newly inputted hour
                populateHour(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    /**
     * Populates the Hour object as soon as a day's open or close hour is entered by the user
     *
     * @param inputtedTime
     */
    public void populateHour(String inputtedTime) {
        //Finds which day is being updated
        switch (day) {
            case "Mon":
                //Finds if the updated time is for the open or close
                if (time.equals("open")) {
                    hours.setMondayOpen(inputtedTime);
                } else {
                    hours.setMondayClose(inputtedTime);
                }
                break;
            case "Tues":
                if (time.equals("open")) {
                    hours.setTuesdayOpen(inputtedTime);
                } else {
                    hours.setTuesdayClose(inputtedTime);
                }
                break;
            case "Wed":
                if (time.equals("open")) {
                    hours.setWednesdayOpen(inputtedTime);
                } else {
                    hours.setWednesdayClose(inputtedTime);
                }
                break;
            case "Thurs":
                if (time.equals("open")) {
                    hours.setThursdayOpen(inputtedTime);
                } else {
                    hours.setThursdayClose(inputtedTime);
                }
                break;
            case "Fri":
                if (time.equals("open")) {
                    hours.setFridayOpen(inputtedTime);
                } else {
                    hours.setFridayClose(inputtedTime);
                }
                break;
            case "Sat":
                if (time.equals("open")) {
                    hours.setSaturdayOpen(inputtedTime);
                } else {
                    hours.setSaturdayClose(inputtedTime);
                }
                break;
            case "Sun":
                if (time.equals("open")) {
                    hours.setSundayOpen(inputtedTime);
                } else {
                    hours.setSundayClose(inputtedTime);
                }
                break;
            default:
                return;
        }
    }

    /**
     * Submits Address, Hours and BusinessInfo into their respective tables then redirects user to
     * the maps fragment
     *
     * @param view
     * @throws InterruptedException
     */
    public void enterAllBusinessInfo(View view) throws InterruptedException {
        //Insert the address into the database
        addressViewModel.insert(address);

        //Grab that newly inserted address
        Address newAddress = addressViewModel.findLastInsertedAddress();

        //Insert the hours into the database
        hoursViewModel.insert(hours);

        //Grab that newly inserted hours
        Hours newHours = hoursViewModel.findLastInsertedHours();

        //Populate Address, Hours, and BusinessLogin Foreign Keys in the BusinessInfo
        businessInfo.setBusinessLoginId(BusinessLoginFragment.getBusinessLoginId());
        businessInfo.setAddressId(newAddress.getAddressId());
        businessInfo.setHoursId(newHours.getHoursId());

        //Insert the BusinessInfo into the database
        businessInfoViewModel.insert(businessInfo);

        //Direct the user back to the maps activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * OnClick method for each day's "Closed All Day" checkboxes
     *
     * @param view
     */
    public void setClosedAllDay(View view) {
        //Strings to be populated in appropriate TextViews
        String open = "Open: Closed";
        String close = "Close: Closed";
        //String to be added to the Hours object for later database insertion
        String closed = "Closed";
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Updates the day's hours appropriately based on which checkbox was pressed
        switch (view.getId()) {
            case R.id.add_hours_checkbox_mon_closed:
                // If the box is checked, update Hours object, set appropriate textViews and disable
                // corresponding "Set Hours" buttons
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_mon);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_mon);
                    textView.setText(close);

                    hours.setMondayOpen(closed);
                    hours.setMondayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_mon);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_mon);
                    button.setEnabled(false);
                }
                // Otherwise re-enable corresponding "Set Hours" buttons
                else {
                    button = findViewById(R.id.add_hours_btn_open_mon);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_mon);
                    button.setEnabled(true);
                }
                break;
            case R.id.add_hours_checkbox_tues_closed:
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_tues);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_tues);
                    textView.setText(close);

                    hours.setTuesdayOpen(closed);
                    hours.setTuesdayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_tues);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_tues);
                    button.setEnabled(false);
                } else {
                    button = findViewById(R.id.add_hours_btn_open_tues);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_tues);
                    button.setEnabled(true);
                }
                break;
            case R.id.add_hours_checkbox_wed_closed:
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_wed);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_wed);
                    textView.setText(close);

                    hours.setWednesdayOpen(closed);
                    hours.setWednesdayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_wed);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_wed);
                    button.setEnabled(false);
                } else {
                    button = findViewById(R.id.add_hours_btn_open_wed);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_wed);
                    button.setEnabled(true);
                }
                break;
            case R.id.add_hours_checkbox_thurs_closed:
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_thurs);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_thurs);
                    textView.setText(close);

                    hours.setThursdayOpen(closed);
                    hours.setThursdayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_thurs);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_thurs);
                    button.setEnabled(false);
                } else {
                    button = findViewById(R.id.add_hours_btn_open_thurs);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_thurs);
                    button.setEnabled(true);
                }
                break;
            case R.id.add_hours_checkbox_fri_closed:
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_fri);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_fri);
                    textView.setText(close);

                    hours.setFridayOpen(closed);
                    hours.setFridayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_fri);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_fri);
                    button.setEnabled(false);
                } else {
                    button = findViewById(R.id.add_hours_btn_open_fri);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_fri);
                    button.setEnabled(true);
                }
                break;
            case R.id.add_hours_checkbox_sat_closed:
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_sat);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_sat);
                    textView.setText(close);

                    hours.setSaturdayOpen(closed);
                    hours.setSaturdayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_sat);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_sat);
                    button.setEnabled(false);
                } else {
                    button = findViewById(R.id.add_hours_btn_open_sat);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_sat);
                    button.setEnabled(true);
                }
                break;
            case R.id.add_hours_checkbox_sun_closed:
                if (checked) {
                    textView = findViewById(R.id.add_hours_textview_open_sun);
                    textView.setText(open);
                    textView = findViewById(R.id.add_hours_textview_close_sun);
                    textView.setText(close);

                    hours.setSundayOpen(closed);
                    hours.setSundayClose(closed);
                    button = findViewById(R.id.add_hours_btn_open_sun);
                    button.setEnabled(false);
                    button = findViewById(R.id.add_hours_btn_close_sun);
                    button.setEnabled(false);
                } else {
                    button = findViewById(R.id.add_hours_btn_open_sun);
                    button.setEnabled(true);
                    button = findViewById(R.id.add_hours_btn_close_sun);
                    button.setEnabled(true);
                }
                break;
            default:
                break;
        }
    }
}