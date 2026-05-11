package com.example.Moventra;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    EditText name, phone, startDate, endDate;
    Uri pdfUri;
    Button confirm;
    TextView tvPdfName;
    int carImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        
        String car_name = getIntent().getStringExtra("carName");
        String car_price = getIntent().getStringExtra("carPrice");
        carImage = getIntent().getIntExtra("carImage", R.drawable.ic_car);
        
        TextView b = findViewById(R.id.bookingCarName);
        b.setText(car_name);
        TextView c = findViewById(R.id.bookingCarPrice);
        c.setText(car_price);
        ImageView img = findViewById(R.id.bookingCarImage);
        if (img != null) {
            img.setImageResource(carImage);
        }

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        confirm = findViewById(R.id.btnConfirm);
        tvPdfName = findViewById(R.id.tvPdfName);
        
        startDate.setOnClickListener(v -> showDatePicker(startDate));
        endDate.setOnClickListener(v -> showDatePicker(endDate));
        
        findViewById(R.id.btnUploadPdf).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            startActivityForResult(intent, 1);
        });

        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            String p = phone.getText().toString().trim();
            String start = startDate.getText().toString();
            String end = endDate.getText().toString();

            if (n.isEmpty() || p.isEmpty() || start.isEmpty() || end.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            } else if (pdfUri == null) {
                Toast.makeText(this, "Please upload license PDF", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, PaymentActivity.class);
                intent.putExtra("car_name", car_name);
                intent.putExtra("car_price", car_price);
                intent.putExtra("car_image", carImage);
                intent.putExtra("booking_date", start + " - " + end);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            tvPdfName.setText("PDF Selected");
            Toast.makeText(this, "License uploaded", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDatePicker(EditText field) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year, month, day) ->
                        field.setText(day + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}