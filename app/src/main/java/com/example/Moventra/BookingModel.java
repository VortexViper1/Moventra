package com.example.Moventra;

public class BookingModel {
    private String name;
    private String date;
    private String price;
    private String status;
    private int image;

    public BookingModel(String name, String date, String price, String status, int image) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getPrice() { return price; }
    public String getStatus() { return status; }
    public int getImage() { return image; }
}