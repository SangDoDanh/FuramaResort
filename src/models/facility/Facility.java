package models.facility;

public abstract class Facility {
    private String id;
    private String name;
    private double usableArea;
    private double rentalCosts;
    private int maximumOfPeople;
    private String rentalStyle;

    public Facility() {
    }

    public Facility(String id, String name, double usableArea, double rentalCosts, int maximumOfPeople, String rentalStyle) {
        this.id = id;
        this.name = name;
        this.usableArea = usableArea;
        this.rentalCosts = rentalCosts;
        this.maximumOfPeople = maximumOfPeople;
        this.rentalStyle = rentalStyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(double usableArea) {
        this.usableArea = usableArea;
    }

    public double getRentalCosts() {
        return rentalCosts;
    }

    public void setRentalCosts(double rentalCosts) {
        this.rentalCosts = rentalCosts;
    }

    public int getMaximumOfPeople() {
        return maximumOfPeople;
    }

    public void setMaximumOfPeople(int maximumOfPeople) {
        this.maximumOfPeople = maximumOfPeople;
    }

    public String getRentalStyle() {
        return rentalStyle;
    }

    public void setRentalStyle(String rentalStyle) {
        this.rentalStyle = rentalStyle;
    }

    @Override
    public String toString() {
        return String.format("%s,,%s,,%s,,%s,,%s,,%s",
                id,name, usableArea, rentalCosts,maximumOfPeople,rentalStyle);
    }
}
