package com.fastag.process.ennum;

public enum VehicleType {

    CAR(50),
    TRUCK(100),
    BUS(80),
    BIKE(30);

    private final double tollAmount;

    VehicleType(double tollAmount) {
        this.tollAmount = tollAmount;
    }

    public double getTollAmount() {
        return tollAmount;
    }

    public static VehicleType from(String type) {
        try {
            return VehicleType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}