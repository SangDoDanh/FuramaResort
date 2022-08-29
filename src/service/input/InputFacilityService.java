package service.input;

import service.impl.get.GetFacilityPropertyService;
import utils.get_set_service.GetService;

public class InputFacilityService {
    private static final String VILLA = "villa";
    private static final String ROOM = "room";
    private static final String HOUSE = "house";
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


    public static void inputFacility(String facilityType) {
        if(facilityType.equalsIgnoreCase(VILLA)) {
            id = new GetFacilityPropertyService().getFacilityId(VILLA);
        } else if(facilityType.equalsIgnoreCase(HOUSE)){
            id = new GetFacilityPropertyService().getFacilityId(HOUSE);
        } else {
            id = new GetFacilityPropertyService().getFacilityId(ROOM);
        }
        name = GetService.getName("Enter name: ");
        usableArea = GetService.getNumberDouble("Enter usable area: ", 4, 10000);
        rentalCosts = GetService.getNumberDouble("Enter rental costs: ", 0, 50000000);
        maximumOfPeople = GetService.getNumberInteger("maximum of people: ", 1, 20);
        rentalStyle = GetService.getRentalStyle();
    }
    public static void inputVilla() {
       inputFacility(VILLA);
        roomStand = GetService.getRomStand();
        numberOfFloors = GetService.getNumberInteger("Number of floors: ", 1, 10);
        swimmingArea = GetService.getNumberDouble("swimming area: ", 30, 200);
    }

    public static void inputHouse() {
        inputFacility(HOUSE);
        roomStand = GetService.getRomStand();
        numberOfFloors = GetService.getNumberInteger("Number of floors", 1, 10);
    }

    public static void inputRoom() {
        inputFacility(ROOM);
        freeService = GetService.getStr("Free service: ");
    }
}
