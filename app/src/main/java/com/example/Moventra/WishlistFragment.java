package com.example.Moventra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistFragment extends Fragment {

    private RecyclerView recyclerView;
    private WishlistAdapter adapter;
    private List<CarModel> wishlistItems;
    private LinearLayout emptyState;

    public WishlistFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerWishlist);
        emptyState = view.findViewById(R.id.emptyWishlistState);

        // Load data from central DataManager for persistence
        wishlistItems = DataManager.getInstance().getWishlist();

        checkEmptyState();

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        
        adapter = new WishlistAdapter(getContext(), wishlistItems, new WishlistAdapter.OnWishlistChangedListener() {
            @Override
            public void onRemoved() {
                checkEmptyState();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void checkEmptyState() {
        if (wishlistItems.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyState.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyState.setVisibility(View.GONE);
        }
    }
}