package com.parkinglot;

import com.parkinglot.enums.VehicleType;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Driver {

    private static ParkingLot parkingLot;

    public static void main(String[] args) {

        int numOfFloors = 2;
        int numOfSlots = 7;

        parkingLot = new ParkingLot(numOfFloors, numOfSlots);
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n========Menu========");
            System.out.println("1. Park a vehicle");
            System.out.println("2. Display the number of free slots per floor for a specific vehicle type");
            System.out.println("3. Display all the free slots per floor for a specific vehicle type");
            System.out.println("4. Display all the occupied slots per floor for a specific vehicle type");
            System.out.println("5. Unpark a vehicle");
            System.out.println("0. Exit");

            System.out.print("> ");
            int response;
            try {
                response = Integer.parseInt(sc.nextLine());
                if(response < 0 || response > 5)
                    throw new NumberFormatException("Improper entry");
            } catch (Exception e) {
                System.out.println("Choose a valid entry between 0-5");
                continue;
            }

            if(response == 0) {
                System.out.println("Goodbye for now. Please come back again to the best parking lot!");
                break;
            }

            switch (response) {
                case 1: {
                    System.out.print("> Enter vehicle type: ");
                    VehicleType vehicleType = VehicleType.valueOf(sc.next().toUpperCase(Locale.ROOT));
                    System.out.print("> Enter registration number: ");
                    int regNum = sc.nextInt();
                    sc.nextLine();
                    System.out.print("> Enter vehicle color: ");
                    String color = sc.nextLine();

                    handleParking(vehicleType, regNum, color);
                    break;
                }

                case 2: {
                    System.out.print("> Vehicle Type: ");
                    VehicleType vehicleType = VehicleType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
                    displayNoOfFreeSlots(vehicleType);
                    break;
                }

                case 3: {
                    System.out.print("> Vehicle Type: ");
                    VehicleType vehicleType = VehicleType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
                    displayFreeSlots(vehicleType);
                    break;
                }

                case 4: {
                    System.out.print("> Vehicle Type: ");
                    VehicleType vehicleType = VehicleType.valueOf(sc.nextLine().toUpperCase(Locale.ROOT));
                    displayOccupiedSlots(vehicleType);
                    break;
                }

                case 5: {
                    System.out.print("> Enter ticket number: ");
                    String ticket = sc.next();
                    handleUnparking(ticket);
                    break;
                }

            }

        }
        // Cars
//        Vehicle v1 = new Vehicle(VehicleType.CAR, 1, "Black");
//        Vehicle v2 = new Vehicle(VehicleType.CAR, 2, "Red");
////
////        // Trucks
//        Vehicle v3 = new Vehicle(VehicleType.TRUCK, 3, "Black");
//        Vehicle v4 = new Vehicle(VehicleType.TRUCK, 4, "Black-red");
//        Vehicle v5 = new Vehicle(VehicleType.TRUCK, 5, "Yellow");

//        Ticket t1 = parkingLot.parkVehicle(v1);
//        Ticket t2 = parkingLot.parkVehicle(v2);
//        Ticket t3 = parkingLot.parkVehicle(v3);
//        Ticket t4 = parkingLot.parkVehicle(v4);
//        Ticket t5 = parkingLot.parkVehicle(v5);

        Ticket t1 = handleParking(VehicleType.CAR, 1, "Black");
        Ticket t2 = handleParking(VehicleType.CAR, 2, "Red");
        Ticket t3 = handleParking(VehicleType.TRUCK, 3, "Black");
        Ticket t4 = handleParking(VehicleType.TRUCK, 4, "Black-red");
        Ticket t5 = handleParking(VehicleType.TRUCK, 5, "Yellow");

        System.out.println(t1 + "\n" + t2 + "\n" + t3 + "\n" + t4 + "\n" + t5);

        displayNoOfFreeSlots(VehicleType.CAR);
        displayNoOfFreeSlots(VehicleType.BIKE);
        displayNoOfFreeSlots(VehicleType.TRUCK);

        displayFreeSlots(VehicleType.CAR);
        displayFreeSlots(VehicleType.BIKE);
        displayFreeSlots(VehicleType.TRUCK);

        displayOccupiedSlots(VehicleType.CAR);
        displayOccupiedSlots(VehicleType.BIKE);
        displayOccupiedSlots(VehicleType.TRUCK);

        handleUnparking(t1.toString());
        handleUnparking(t2.toString());
        handleUnparking(t3.toString());
        handleUnparking(t4.toString());
        handleUnparking(t5.toString());

    }

    private static void displayOccupiedSlots(VehicleType vehicleType) {

        System.out.println("======= Occupied slots for type: " + vehicleType + " ========");
        List<List<Slot>> slots = parkingLot.getOccupiedSlots(vehicleType);
        for(int i = 0; i < slots.size(); i++) {
            System.out.print("Floor " + (i+1) + ": ");
            for(Slot slot: slots.get(i)) {
                System.out.print(slot.getSlotNum() + " ");
            }
            System.out.println();
        }
    }

    private static void displayFreeSlots(VehicleType vehicleType) {

        System.out.println("======= Free slots for type: " + vehicleType + " ========");
        List<List<Slot>> slots = parkingLot.getFreeSlots(vehicleType);
        for(int i = 0; i < slots.size(); i++) {
            System.out.print("Floor " + (i+1) + ": ");
            for(Slot slot: slots.get(i)) {
                System.out.print(slot.getSlotNum() + " ");
            }
            System.out.println();
        }
    }

    private static void displayNoOfFreeSlots(VehicleType vehicleType) {

        System.out.println("======= Free slots count for type: " + vehicleType + " ========");
        List<Integer> noOfSlotsPerFloor = parkingLot.getFreeCount(vehicleType);
        for(int i = 0; i < noOfSlotsPerFloor.size(); i++) {
            System.out.println("Floor " + (i+1) + ": " + noOfSlotsPerFloor.get(i) + " free slots");
        }
    }

    private static Vehicle handleUnparking(String ticket) {
        Vehicle vehicle = parkingLot.unparkVehicle(ticket);

        if(vehicle == null) {
            System.out.println("Invalid ticket");
        } else {
            System.out.println("Unparked vehicle with registration number " + vehicle.getRegistrationNum() + " and color: " + vehicle.getColor());
        }

        return vehicle;
    }

    private static Ticket handleParking(VehicleType vehicleType, int regNum, String color) {

        Vehicle vehicle = new Vehicle(vehicleType, regNum, color);
        Ticket ticket = parkingLot.parkVehicle(vehicle);

        if (ticket == Ticket.EMPTY) {
            System.out.println("Parking Lot Full for " + vehicleType);
        } else {
            System.out.println("Parked vehicle. Ticket - " + ticket);
        }

        return ticket;
    }

}
