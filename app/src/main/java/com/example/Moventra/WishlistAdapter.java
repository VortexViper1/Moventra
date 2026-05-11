package com.example.Moventra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private Context context;
    private List<CarModel> wishlist;
    private OnWishlistChangedListener listener;

    public interface OnWishlistChangedListener {
        void onRemoved();
    }

    public WishlistAdapter(Context context, List<CarModel> wishlist, OnWishlistChangedListener listener) {
        this.context = context;
        this.wishlist = wishlist;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        View btnRemove;
        TextView carName, carBrand, carPrice;
        MaterialButton btnBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.wishlistCarImage);
            btnRemove = itemView.findViewById(R.id.btnRemoveWishlist);
            carName = itemView.findViewById(R.id.wishlistCarName);
            carBrand = itemView.findViewById(R.id.wishlistCarBrand);
            carPrice = itemView.findViewById(R.id.wishlistCarPrice);
            btnBook = itemView.findViewById(R.id.btnBookWishlist);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarModel car = wishlist.get(holder.getAdapterPosition());

        holder.carName.setText(car.getName());
        holder.carBrand.setText(car.getBrand().toUpperCase());
        holder.carPrice.setText(car.getPrice());
        holder.carImage.setImageResource(car.getImage());

        holder.btnRemove.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                // Remove from central DataManager to ensure it doesn't reappear
                DataManager.getInstance().removeFromWishlist(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, wishlist.size());
                if (listener != null) listener.onRemoved();
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnBook.setOnClickListener(v -> {
            // "Rent Now" now opens the CarDetailActivity just like in Explore
            Intent intent = new Intent(context, CarDetailActivity.class);
            intent.putExtra("name", car.getName());
            intent.putExtra("brand", car.getBrand());
            intent.putExtra("price", car.getPrice());
            intent.putExtra("rating", car.getRating());
            intent.putExtra("transmission", car.getTransmission());
            intent.putExtra("fuel", car.getFuelType());
            intent.putExtra("image", car.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wishlist.size();
    }
}