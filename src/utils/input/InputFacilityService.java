package utils.input;

import utils.get_set_service.GetService;

public class InputFacilityService {
    public static String id;
    public static String name;
    public static double usableArea;
    public static double rentalCosts;
    public static int maximumOfPeople;
    public static String rentalStyle;
    public static String freeService;
    public static double swimmingArea;
    public static String roomStand;
    public static int numberOfFloors;

    public static void inputFacility() {
        id = GetService.getStr("Enter id: ");
        name = GetService.getStr("Enter name: ");
        usableArea = GetService.getNumberDouble("Enter usable area: ", 4, 10000);
        rentalCosts = GetService.getNumberDouble("Enter rental costs: ", 0, 50000000);
        maximumOfPeople = GetService.getNumberInteger("maximum of people: ", 1, 20);
        rentalStyle = GetService.getRentalStyle();
    }
    public static void inputVilla() {
       inputHouse();
       swimmingArea = GetService.getNumberDouble("swiming area:", 30, 200);
    }

    public static void inputHouse() {
        inputFacility();
        roomStand = GetService.getRomStand();
        numberOfFloors = GetService.getNumberInteger("Number of floors", 1, 10);
    }

    public static void inputRoom() {
        inputFacility();
        freeService = GetService.getStr("Free service: ");
    }
}
