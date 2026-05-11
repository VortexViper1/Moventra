package com.example.Moventra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private List<CarModel> carList;
    private Context context;

    public CarAdapter(Context context, List<CarModel> carList) {
        this.context = context;
        this.carList = carList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carName, carBrand, carPrice, carTransmission, carFuel;
        RatingBar carRating;
        MaterialButton btnBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.carImage);
            carName = itemView.findViewById(R.id.carName);
            carBrand = itemView.findViewById(R.id.carBrand);
            carPrice = itemView.findViewById(R.id.carPrice);
            carRating = itemView.findViewById(R.id.carRating);
            carTransmission = itemView.findViewById(R.id.carTransmission);
            carFuel = itemView.findViewById(R.id.carFuel);
            btnBook = itemView.findViewById(R.id.btnBook);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarModel car = carList.get(position);

        holder.carName.setText(car.getName());
        holder.carBrand.setText(car.getBrand());
        holder.carPrice.setText(car.getPrice());
        try {
            holder.carRating.setRating(Float.parseFloat(car.getRating()));
        } catch (Exception e) {
            holder.carRating.setRating(0);
        }
        holder.carTransmission.setText(car.getTransmission());
        holder.carFuel.setText(car.getFuelType());
        holder.carImage.setImageResource(car.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarDetailActivity.class);
            intent.putExtra("name", car.getName());
            intent.putExtra("brand", car.getBrand());
            intent.putExtra("price", car.getPrice());
            intent.putExtra("rating", car.getRating());
            intent.putExtra("transmission", car.getTransmission());
            intent.putExtra("fuel", car.getFuelType());
            intent.putExtra("image", car.getImage());

            if (context instanceof Activity) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, holder.carImage, "carImageTransition");
                context.startActivity(intent, options.toBundle());
            } else {
                context.startActivity(intent);
            }
        });

        holder.btnBook.setOnClickListener(v -> {
            // Also trigger the same transition when clicking "Details" button
            holder.itemView.performClick();
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}