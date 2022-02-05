package com.parkinglot;

public class Ticket {
    private final int floorNum;
    private final int slotNum;
    private final Vehicle vehicle;

    public static final Ticket EMPTY = new Ticket(null, -1, -1);

    public Ticket(Vehicle vehicle, int floorNum, int slotNum) {
        this.vehicle = vehicle;
        this.floorNum = floorNum;
        this.slotNum = slotNum;
    }

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
