package com.example.Moventra;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private List<CarModel> wishlist;
    private List<NotificationModel> notifications;
    private List<BookingModel> bookings;

    private DataManager() {
        wishlist = new ArrayList<>();
        notifications = new ArrayList<>();
        bookings = new ArrayList<>();
        loadInitialData();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void loadInitialData() {
        // Initial Wishlist
        wishlist.add(new CarModel("911 Carrera S", "Porsche", "₹2500/day", "4.6", "Auto", "Petrol", R.drawable.porsche));
        wishlist.add(new CarModel("MayBach GLS", "Mercedes", "₹3000/day", "4.9", "Auto", "Petrol", R.drawable.benz));
        wishlist.add(new CarModel("Model 3", "Tesla", "₹4000/day", "4.7", "Auto", "Electric", R.drawable.tesla));
        wishlist.add(new CarModel("Urus", "Lamborghini", "₹25000/day", "5.0", "Auto", "Petrol", R.drawable.lamborgini));
        // Initial Notifications
        notifications.add(new NotificationModel("New Arrival: Audi RS7 🏎️", "The Audi RS7 is now available! V8 Twin-Turbo, 600HP.", "10 mins ago", false));
        notifications.add(new NotificationModel("Booking Confirmed ✅", "Your booking for Tesla Model S has been confirmed.", "2 hours ago", false));
        notifications.add(new NotificationModel("Special Offer! 🎁", "Get 20% OFF on all SUVs this weekend.", "5 hours ago", true));
        
        // Initial Bookings (Samples)
        bookings.add(new BookingModel("M5 CS", "12 Oct - 15 Oct", "₹6000", "Completed", R.drawable.bmw));
    }

    public List<CarModel> getWishlist() { return wishlist; }
    public List<NotificationModel> getNotifications() { return notifications; }
    public List<BookingModel> getBookings() { return bookings; }

    public void addBooking(BookingModel booking) {
        bookings.add(0, booking); // Add to top
    }

    public void removeFromWishlist(int position) {
        if (position >= 0 && position < wishlist.size()) {
            wishlist.remove(position);
        }
    }

    public void markAllNotificationsRead() {
        for (int i = 0; i < notifications.size(); i++) {
            NotificationModel old = notifications.get(i);
            notifications.set(i, new NotificationModel(old.getTitle(), old.getMessage(), old.getTime(), true));
        }
    }
}