package models.facility;

import models.facility.Facility;

public class House extends Facility {
    private String roomStand;
    private int numberOfFloors;

    public House() {
    }

    public House(String id, String name, double usableArea, double rentalCosts, int maximumOfPeople, String rentalStyle, String roomStand, int numberOfFloors) {
        super(id, name, usableArea, rentalCosts, maximumOfPeople, rentalStyle);
        this.roomStand = roomStand;
        this.numberOfFloors = numberOfFloors;
    }

    public String getRoomStand() {
        return roomStand;
    }

    public void setRoomStand(String roomStand) {
        this.roomStand = roomStand;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",,%s,,%s", roomStand, numberOfFloors);
    }
}
