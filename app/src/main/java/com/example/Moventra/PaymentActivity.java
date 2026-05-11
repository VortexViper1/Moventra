package com.example.Moventra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class PaymentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView summaryCarName, summaryCarPrice, totalAmount;
    private RadioGroup paymentGroup;
    private MaterialButton btnPay;
    private String carName, carPrice, bookingDate;
    private int carImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Get Data from Intent
        carName = getIntent().getStringExtra("car_name");
        carPrice = getIntent().getStringExtra("car_price");
        carImage = getIntent().getIntExtra("car_image", R.drawable.ic_car);
        bookingDate = getIntent().getStringExtra("booking_date");

        initViews();
        setupToolbar();
        displayData();

        btnPay.setOnClickListener(v -> {
            if (paymentGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            } else {
                processPayment();
            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.paymentToolbar);
        summaryCarName = findViewById(R.id.summaryCarName);
        summaryCarPrice = findViewById(R.id.summaryCarPrice);
        totalAmount = findViewById(R.id.totalAmount);
        paymentGroup = findViewById(R.id.paymentGroup);
        btnPay = findViewById(R.id.btnPay);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void displayData() {
        if (carName != null) summaryCarName.setText(carName);
        if (carPrice != null) {
            summaryCarPrice.setText(carPrice);
            totalAmount.setText(carPrice);
        }
    }

    private void processPayment() {
        AlertDialog progressDialog = new AlertDialog.Builder(this)
                .setMessage("Processing payment...")
                .setCancelable(false)
                .show();

        new Handler().postDelayed(() -> {
            progressDialog.dismiss();
            
            // SAVE TO HISTORY (DataManager)
            BookingModel newBooking = new BookingModel(
                    carName, 
                    bookingDate != null ? bookingDate : "Today", 
                    carPrice, 
                    "Confirmed", 
                    carImage
            );
            DataManager.getInstance().addBooking(newBooking);

            showSuccessDialog();
        }, 2000);
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success!")
                .setMessage("Payment received. Your car is booked.")
                .setPositiveButton("Go to Home", (dialog, which) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false)
                .show();
    }
}