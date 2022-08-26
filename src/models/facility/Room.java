package models.facility;

import models.facility.Facility;

public class Room extends Facility {
    private String freeService;

    public Room() {
    }

    public Room(String id, String name, double usableArea, double rentalCosts, int maximumOfPeople, String rentalStyle, String freeService) {
        super(id, name, usableArea, rentalCosts, maximumOfPeople, rentalStyle);
        this.freeService = freeService;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",,%s", freeService);
    }
}
