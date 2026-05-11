package com.example.Moventra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<BookingModel> list;
    private BookingAdapter adapter;
    private LinearLayout emptyState;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerBookings);
        emptyState = view.findViewById(R.id.emptyHistoryState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = DataManager.getInstance().getBookings();

        if (list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            if (emptyState != null) emptyState.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            if (emptyState != null) emptyState.setVisibility(View.GONE);
            
            adapter = new BookingAdapter(getContext(), list);
            recyclerView.setAdapter(adapter);
        }
    }
}