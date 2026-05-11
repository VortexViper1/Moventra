package com.example.Moventra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;

public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<NotificationModel> notificationList;

    public NotificationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerNotifications);
        ExtendedFloatingActionButton btnMarkRead = view.findViewById(R.id.btnMarkAllRead);

        // Load persisted data from DataManager
        notificationList = DataManager.getInstance().getNotifications();

        adapter = new NotificationAdapter(getContext(), notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        btnMarkRead.setOnClickListener(v -> {
            DataManager.getInstance().markAllNotificationsRead();
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "All notifications marked as read", Toast.LENGTH_SHORT).show();
        });
    }
}