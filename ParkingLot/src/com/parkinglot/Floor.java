package com.parkinglot;

import com.parkinglot.enums.SlotStatus;
import com.parkinglot.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private final List<Slot> slots;
    private final int floorNum;

    public Floor(int numOfSlots, int floorNum) {
        this.floorNum = floorNum;
        slots = new ArrayList<>();
        for(int i = 0; i < numOfSlots; i++) {
            slots.add(Slot.getInstance(i+1));
        }
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public Slot getAvailableSlot(VehicleType vehicleType) {
        Slot availableSlot = Slot.EMPTY;
        for(Slot slot: slots) {
            if(slot.checkForVehicleType(vehicleType, SlotStatus.AVAILABLE)) {
                availableSlot = slot;
                break;
            }
        }

        return availableSlot;
    }
}
