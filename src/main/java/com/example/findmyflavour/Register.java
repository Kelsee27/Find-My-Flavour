package com.example.findmyflavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findmyflavour.data.Models.BusinessLogin;
import com.example.findmyflavour.data.ViewModels.BusinessLoginViewModel;

/**
 * Contains methods and validation checks that allow a business user to register onto the app.
 * This class is used in conjunction with the activity_register.xml layout.
 */
public class Register extends AppCompatActivity {
    private BusinessLoginViewModel businessLoginViewModel;

    EditText ownerName;
    EditText email;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        businessLoginViewModel = new ViewModelProvider(this).get(BusinessLoginViewModel.class);

        ownerName = findViewById(R.id.register_name_edittext);
        email = findViewById(R.id.register_email_edittext);
        password = findViewById(R.id.register_password_edittext);
        confirmPassword = findViewById(R.id.register_confirm_password_edittext);
    }

    /**
     * Enters the BusinessLogin into the database based on user input ONLY if it passes all validation
     * checks
     *
     * @param view
     */
    public void submitRegistration(View view) {
        //If one of more edit text fields are blank, do not proceed
        if (!checkAllFieldsAreFilled()) {
            return;
        }
        //If the email is already associated with an account, do not proceed
        if (checkForExistingEmail()) {
            email.setError("Email has already been registered");
            return;
        }
        //If the passwords do not match, do not proceed
        if (!checkForMatchingPassword()) {
            password.setError("Passwords must match");
            confirmPassword.setError("Passwords must match");

            return;
        }

        BusinessLogin businessLogin = new BusinessLogin();
        businessLogin.setOwnerName(ownerName.getText().toString());
        businessLogin.setEmail(email.getText().toString());
        businessLogin.setPassword(password.getText().toString());

        businessLoginViewModel.insert(businessLogin);

        businessLogin = businessLoginViewModel.getByEmail(email.getText().toString());

        BusinessLoginFragment.setBusinessLoginId(businessLogin.getBusinessLoginId());

        //Start the AddBusiness activity
        Intent intent = new Intent(this, AddBusiness.class);
        startActivity(intent);
    }

    /**
     * Checks that all edit text fields are filled out, otherwise set an error for the blank EditText
     *
     * @return boolean
     */
    public boolean checkAllFieldsAreFilled() {
        boolean result = true;
        if (TextUtils.isEmpty(ownerName.getText().toString())) {
            ownerName.setError("The name of the owner is required");
            result = false;
        }
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("An email is required");
            result = false;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Choosing a password is required");
            result = false;
        }
        if (TextUtils.isEmpty(confirmPassword.getText().toString())) {
            confirmPassword.setError("Re-entering your password is required");
            result = false;
        }
        return result;
    }

    /**
     * Checks if the email the user provided has already been registered to an account
     *
     * @return boolean
     */
    public boolean checkForExistingEmail() {
        BusinessLogin businessLogin = businessLoginViewModel.getByEmail(email.getText().toString());

        //If the query returned a login credential with the email entered by the user
        if (businessLogin != null) {
            return true;
        }
        return false;
    }

    /**
     * Ensures that the password and confirmation of the password the user enters matches
     *
     * @return boolean
     */
    public boolean checkForMatchingPassword() {
        return password.getText().toString().equals(confirmPassword.getText().toString());
    }
}