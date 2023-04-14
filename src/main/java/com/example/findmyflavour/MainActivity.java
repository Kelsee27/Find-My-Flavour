package com.example.findmyflavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Holds the bottom navigation bar and methods that control fragment transactions.
 * This class is used in conjunction with the activity_main.xml layout.
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navbar_show_map);
    }

    MapsFragment mapsFragment = new MapsFragment();
    BusinessLoginFragment businessLoginFragment = new BusinessLoginFragment();
    ManageBusiness manageBusiness = new ManageBusiness();

    /**
     * Determines which fragment to display based on the user's choice on the bottom navigation bar.
     *
     * @param item
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Chooses which fragment to display based on the bottom nav bar
        switch (item.getItemId()) {
            case R.id.navbar_show_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, mapsFragment).commit();
                return true;
            //If the user is not logged in, the login fragment will appear, otherwise the manage
            // business fragment will appear
            case R.id.navbar_manage_business:
                if (BusinessLoginFragment.getBusinessLoginId() == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, businessLoginFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, manageBusiness).commit();
                }
                return true;
        }
        return false;
    }
}