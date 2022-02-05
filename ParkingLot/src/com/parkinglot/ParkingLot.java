package com.parkinglot;

import com.parkinglot.enums.SlotStatus;
import com.parkinglot.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {
    private final List<Floor> floors;

    public ParkingLot(int numOfFloors, int numOfSlots) {
        floors = new ArrayList<>();
        for(int i = 0; i < numOfFloors; i++) {
            floors.add(new Floor(numOfSlots, i+1));
        }
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for(Floor floor: floors) {
            Slot slot = floor.getAvailableSlot(vehicle.getType());
            if(slot != Slot.EMPTY) {
                Ticket ticket = new Ticket(vehicle, floor.getFloorNum(), slot.getSlotNum());
                slot.park(ticket);
                return ticket;
            }
        }

        return Ticket.EMPTY;
    }

    public Vehicle unparkVehicle(String ticket) {
        for(Floor floor: floors) {
            for(Slot slot: floor.getSlots()) {
                Ticket ticketForSlot = slot.getTicketForSlot();
                if(ticketForSlot.toString().equalsIgnoreCase(ticket)) {
                    slot.unpark();
                    return ticketForSlot.getVehicle();
                }
            }
        }

        return null;
    }

    public List<List<Slot>> getFreeSlots(VehicleType vehicleType) {
        return getSlots(vehicleType, SlotStatus.AVAILABLE);
    }

    public List<Integer> getFreeCount(VehicleType vehicleType) {
        return getFreeSlots(vehicleType).stream().map(slotsPerFloor -> slotsPerFloor.size())
                .collect(Collectors.toList());
    }

    public List<List<Slot>> getOccupiedSlots(VehicleType vehicleType) {
        return getSlots(vehicleType, SlotStatus.OCCUPIED);
    }

    private List<List<Slot>> getSlots(VehicleType vehicleType, SlotStatus slotStatus) {
        List<List<Slot>> allSlots = new ArrayList<>();
        for(Floor floor: floors) {
            List<Slot> slotsPerFloor = new ArrayList<>();
            for(Slot slot: floor.getSlots()) {
                if(slot.checkForVehicleType(vehicleType, slotStatus))
                    slotsPerFloor.add(slot);
            }
            allSlots.add(slotsPerFloor);
        }

        return allSlots;
    }
}
