package models.facility;

public class Villa extends House {
    private double swimmingArea;

    public Villa() {
    }

    public Villa(String id, String name, double usableArea, double rentalCosts, int maximumOfPeople,
                 String rentalStyle, String roomStand, int numberOfFloors, double swimmingArea) {
        super(id, name, usableArea, rentalCosts, maximumOfPeople, rentalStyle, roomStand, numberOfFloors);
        this.swimmingArea = swimmingArea;
    }

    public double getSwimmingArea() {
        return swimmingArea;
    }

    public void setSwimmingArea(double swimmingArea) {
        this.swimmingArea = swimmingArea;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(",,%s", swimmingArea);
    }
}
