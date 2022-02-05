package com.parkinglot;

public record Ticket(Vehicle vehicle, int floorNum, int slotNum) {
    public static final Ticket EMPTY = new Ticket(null, -1, -1);

    public int getFloorNum() {
        return floorNum;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String toString() {
        return "PR1234_" + this.floorNum + "_" + this.slotNum;
    }
}
