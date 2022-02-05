package com.parkinglot;

import com.parkinglot.enums.SlotStatus;
import com.parkinglot.enums.VehicleType;

public class Slot {

    private final VehicleType typeAllowed;
    private SlotStatus slotStatus;
    private Ticket ticketForSlot;
    private final int slotNum;

    public static final Slot EMPTY = new Slot(Ticket.EMPTY, null, SlotStatus.AVAILABLE, -1);

    private Slot(Ticket ticket, VehicleType typeAllowed, SlotStatus slotStatus, int slotNum) {
        this.ticketForSlot = ticket;
        this.typeAllowed = typeAllowed;
        this.slotStatus = slotStatus;
        this.slotNum = slotNum;
    }

    public static Slot getInstance(int index) {
        if(index == 1) {
            return new Slot(Ticket.EMPTY, VehicleType.TRUCK, SlotStatus.AVAILABLE, index);
        }
        if(index == 2 || index == 3) {
            return new Slot(Ticket.EMPTY, VehicleType.BIKE, SlotStatus.AVAILABLE, index);
        }

        return new Slot(Ticket.EMPTY, VehicleType.CAR, SlotStatus.AVAILABLE, index);
    }

    public VehicleType getTypeAllowed() {
        return typeAllowed;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public boolean checkForVehicleType(VehicleType vehicleType, SlotStatus slotStatus) {
        return (this.slotStatus == slotStatus && typeAllowed == vehicleType);
    }

    public Ticket getTicketForSlot() {
        return ticketForSlot;
    }

    public void unpark() {
        this.ticketForSlot = Ticket.EMPTY;
        this.slotStatus = SlotStatus.AVAILABLE;
    }

    public void park(Ticket ticket) {
        this.ticketForSlot = ticket;
        this.slotStatus = SlotStatus.OCCUPIED;
    }
}
