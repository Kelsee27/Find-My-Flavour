package com.example.findmyflavour;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findmyflavour.data.Models.BusinessLogin;
import com.example.findmyflavour.data.ViewModels.BusinessLoginViewModel;

/**
 * Contains methods and validation checks that allow a business user to log into their account.
 * This class is used in conjunction with the fragment_business_login.xml layout.
 */
public class BusinessLoginFragment extends Fragment implements View.OnClickListener {
    //Static variable that will be changed to the user's business login Id upon successful login
    public static int BUSINESS_LOGIN_ID = 0;

    BusinessLoginViewModel businessLoginViewModel;
    EditText emailEditText;
    EditText passwordEditText;

    public BusinessLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_business_login, container, false);
        emailEditText = (EditText) v.findViewById(R.id.login_edittext_email);
        passwordEditText = (EditText) v.findViewById(R.id.login_edittext_password);
        Button loginBtn = (Button) v.findViewById(R.id.login_fragment_login_btn);
        Button registerBtn = (Button) v.findViewById(R.id.login_fragment_register_btn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    /**
     * Setting onClick listener for Login and Register buttons
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_login_btn:
                //If the login is valid, switch to the manage business fragment
                if (verifyLogin()) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    ManageBusiness manageBusiness = new ManageBusiness();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, manageBusiness).commit();
                }
                break;
            //Start the register activity when register button is clicked
            case R.id.login_fragment_register_btn:
                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * Verifies the user's entered credentials. Upon success, the static variable BUSINESS_LOGIN_ID
     * will be set to their id. Otherwise EditTexts will have errors appear
     */
    public boolean verifyLogin() {
        businessLoginViewModel = new ViewModelProvider(this).get(BusinessLoginViewModel.class);

        BusinessLogin businessLogin = businessLoginViewModel.getByLogin(
                emailEditText.getText().toString(), passwordEditText.getText().toString());

        //If the credentials are saved in the database, set the BUSINESS_LOGIN_ID to the business's
        // id. Otherwise set error messages
        if (businessLogin != null) {
            BUSINESS_LOGIN_ID = businessLogin.getBusinessLoginId();
            return true;
        } else {
            emailEditText.setError("Invalid Credentials");
            passwordEditText.setError("Invalid Credentials");
        }
        return false;
    }

    /**
     * Returns the businessLogin ID for the current business user
     *
     * @return int
     */
    public static int getBusinessLoginId() {
        return BUSINESS_LOGIN_ID;
    }

    /**
     * Sets the businessLogin ID for the current business user
     */
    public static void setBusinessLoginId(int id) {
        BUSINESS_LOGIN_ID = id;
    }
}