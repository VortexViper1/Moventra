package com.example.Moventra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<CarModel> list;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        list.add(new CarModel("M5 CS", "BMW", "₹2000/day", "4.8", "Automatic", "Petrol", R.drawable.bmw));
        list.add(new CarModel("RS7", "Audi", "₹1800/day", "4.9", "Automatic", "Petrol", R.drawable.audi));
        list.add(new CarModel("Swift ZXI", "Suzuki", "₹1000/day", "4.5", "Manual", "Petrol", R.drawable.suziki));
        list.add(new CarModel("Model 3", "Tesla", "₹4000/day", "4.7", "Automatic", "Electric", R.drawable.tesla));
        list.add(new CarModel("911","Porsche","₹2500/day","4.6","Automatic","Petrol",R.drawable.porsche));
        list.add(new CarModel("MayBach GLS", "Maybach", "₹3000/day", "4.9", "Automatic", "Petrol", R.drawable.benz));
        list.add(new CarModel("i20 sport", "Hyundai", "₹1500/day", "4.4", "Manual", "Petrol", R.drawable.hyundai));
        list.add(new CarModel("SF90", "Ferrari", "₹3500/day", "4.9", "Automatic", "Petrol", R.drawable.ferari));
        list.add(new CarModel("Virtus", "Volkswagen", "₹1200/day", "4.3", "Manual", "Petrol", R.drawable.volls));
        list.add(new CarModel("Ranger", "Ford", "₹1800/day", "4.7", "Automatic", "Petrol", R.drawable.ford));
        list.add(new CarModel("Fortuner", "Toyota", "₹2200/day", "4.6", "Automatic", "Petrol", R.drawable.toyota));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CarAdapter(getContext(), list));

        return view;
    }
}