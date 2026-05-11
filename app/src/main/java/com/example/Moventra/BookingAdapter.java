package com.example.Moventra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private List<BookingModel> list;
    private Context context;

    public BookingAdapter(Context context, List<BookingModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, price, status;
        ImageView carImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bookingName);
            date = itemView.findViewById(R.id.bookingDate);
            price = itemView.findViewById(R.id.bookingPrice);
            status = itemView.findViewById(R.id.bookingStatus);
            carImage = itemView.findViewById(R.id.bookedCarImage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingModel b = list.get(position);

        holder.name.setText(b.getName());
        holder.date.setText(b.getDate());
        holder.price.setText(b.getPrice());
        
        if (holder.status != null) {
            holder.status.setText(b.getStatus());
        }
        
        if (holder.carImage != null) {
            holder.carImage.setImageResource(b.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}