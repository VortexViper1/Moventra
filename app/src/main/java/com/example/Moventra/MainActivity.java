package com.example.Moventra;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);

        // Default screen → Home (Explore)
        loadFragment(new HomeFragment());

        // Bottom Navigation Click
        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selected = null;

            if (item.getItemId() == R.id.favorites) {
                selected = new WishlistFragment();
            }
            else if (item.getItemId() == R.id.bookings) {
                selected = new HistoryFragment();
            }
            else if (item.getItemId() == R.id.notifications) {
                selected = new NotificationFragment();
            }
            else if (item.getItemId() == R.id.profile) {
                selected = new ProfileFragment();
            }

            return loadFragment(selected);
        });

        fab.setOnClickListener(v -> {
            loadFragment(new HomeFragment());
        });
    }

    // Method to switch fragments
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}