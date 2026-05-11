package com.example.Moventra;

public class CarModel {
    private String name;
    private String brand;
    private String price;
    private String rating;
    private String transmission;
    private String fuelType;
    private int image;

    public CarModel(String name, String brand, String price, String rating, String transmission, String fuelType, int image) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.image = image;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getPrice() { return price; }
    public String getRating() { return rating; }
    public String getTransmission() { return transmission; }
    public String getFuelType() { return fuelType; }
    public int getImage() { return image; }
}