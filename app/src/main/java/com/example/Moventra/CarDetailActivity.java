package com.example.Moventra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class CarDetailActivity extends AppCompatActivity {

    ImageView carImage;
    TextView carName, carBrand, carPrice, carTransmission, carFuel;
    RatingBar carRating;
    MaterialButton btnBookNow;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> finish());


        carImage = findViewById(R.id.carImageDetail);
        carName = findViewById(R.id.carNameDetail);
        carBrand = findViewById(R.id.carBrandDetail);
        carPrice = findViewById(R.id.carPriceDetail);
        carTransmission = findViewById(R.id.carTransmissionDetail);
        carFuel = findViewById(R.id.carFuelDetail);
        carRating = findViewById(R.id.carRatingDetail);
        btnBookNow = findViewById(R.id.btnBookNowDetail);
        String name = getIntent().getStringExtra("name");
        String brand = getIntent().getStringExtra("brand");
        String price = getIntent().getStringExtra("price");
        String rating = getIntent().getStringExtra("rating");
        String transmission = getIntent().getStringExtra("transmission");
        String fuel = getIntent().getStringExtra("fuel");
        int image = getIntent().getIntExtra("image", R.drawable.ic_car);

        carName.setText(name);
        carBrand.setText(brand);
        carPrice.setText(price);
        carTransmission.setText(transmission);
        carFuel.setText(fuel);

        try {
            carRating.setRating(Float.parseFloat(rating));
        } catch (Exception e) {
            carRating.setRating(0f);
        }

        carImage.setImageResource(image);

        btnBookNow.setOnClickListener(v -> {
            Toast.makeText(this, "Booking " + name, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BookingActivity.class);
            intent.putExtra("carName", name);
            intent.putExtra("carPrice", price);
            intent.putExtra("carImage", image);
            startActivity(intent);
        });
    }
}