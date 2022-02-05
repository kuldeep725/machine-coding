package com.parkinglot;

import com.parkinglot.enums.VehicleType;

public class Vehicle {

    private VehicleType type;
    private int registrationNum;
    private String color;

    public Vehicle(VehicleType type, int registrationNum, String color) {
        this.type = type;
        this.registrationNum = registrationNum;
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(int registrationNum) {
        this.registrationNum = registrationNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
