package com.example.Moventra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView name, email, profileInitial;
    private View logoutBtn;
    private LinearLayout btnMyBookings, btnFavorites, btnNotifications;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize Views
        name = view.findViewById(R.id.profileName);
        email = view.findViewById(R.id.profileEmail);
        profileInitial = view.findViewById(R.id.profileInitial);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        
        btnMyBookings = view.findViewById(R.id.btnMyBookings);
        btnFavorites = view.findViewById(R.id.btnFavorites);
        btnNotifications = view.findViewById(R.id.btnNotifications);

        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String userName = sp.getString("name", "User");
        String userEmail = sp.getString("email", "No Email");

        name.setText(userName);
        email.setText(userEmail);

        if (userName != null && !userName.isEmpty()) {
            profileInitial.setText(String.valueOf(userName.charAt(0)).toUpperCase());
        }

        btnMyBookings.setOnClickListener(v -> {

            Toast.makeText(getContext(), "Opening My Bookings", Toast.LENGTH_SHORT).show();
        });

        btnFavorites.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Opening Saved Vehicles", Toast.LENGTH_SHORT).show();
        });

        btnNotifications.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Opening Notifications", Toast.LENGTH_SHORT).show();
        });

        logoutBtn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });

        return view;
    }
}
