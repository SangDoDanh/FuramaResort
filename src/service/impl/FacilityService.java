package service.impl;

import models.facility.Facility;
import models.facility.House;
import models.facility.Room;
import models.facility.Villa;
import service.IFacilityService;
import utils.get_set_service.GetService;
import utils.input.InputFacilityService;
import utils.read_write.ReadFile;
import utils.read_write.WriteFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FacilityService implements IFacilityService {
    private final static String PATH_VILLA = "src/data/villa.csv";
    private final static String PATH_ROOM = "src/data/room.csv";
    private final static String PATH_HOUSE = "src/data/house.csv";
    private final static String[] PATH_FACILITY = {PATH_HOUSE, PATH_VILLA, PATH_ROOM};
    Map<Facility,Integer> facilityListMap;
    @Override
    public void display() {
        facilityListMap = readFileFacility(PATH_FACILITY);
        if(facilityListMap.size() == 0) {
            System.out.println("Facility is empty!");
        } else {
            for(Facility f : facilityListMap.keySet()) {
                System.out.println(f);
            }
        }
    }
    public Map<Facility,Integer> readFileFacility(String[] pathList) {
        Map<Facility,Integer> facilityMap = new LinkedHashMap<>();
        for(String p : pathList) {
            readFileFacilityDetail(p, facilityMap);
        }
        return facilityMap;
    }

    private void readFileFacilityDetail(String path, Map<Facility, Integer> facilityAll) {
        List<String> facilityString = ReadFile.readFile(path);
        String[] propertyOfFacility;
        Facility facility = null;
        for(String f : facilityString) {
            propertyOfFacility = f.split(",,");
            if(propertyOfFacility[0].startsWith("VL")) {
                facility = getVilla(propertyOfFacility);
            } else if (propertyOfFacility[0].startsWith("HO")) {
                facility = getHouse(propertyOfFacility);
            } else {
                facility = getRoom(propertyOfFacility);
            }
            facilityAll.put(facility,0);
        }
    }

    private Facility getRoom(String[] propertyOfFacility) {
        Facility facility;
        facility = new Room(propertyOfFacility[0],
                propertyOfFacility[1],
                Double.parseDouble(propertyOfFacility[2]),
                Double.parseDouble(propertyOfFacility[3]),
                Integer.parseInt(propertyOfFacility[4]),
                propertyOfFacility[5],
                propertyOfFacility[6]);
        return facility;
    }

    private Facility getHouse(String[] propertyOfFacility) {
        Facility facility;
        facility = new House(propertyOfFacility[0],
                propertyOfFacility[1],
                Double.parseDouble(propertyOfFacility[2]),
                Double.parseDouble(propertyOfFacility[3]),
                Integer.parseInt(propertyOfFacility[4]),
                propertyOfFacility[5],
                propertyOfFacility[6],
                Integer.parseInt(propertyOfFacility[7]));
        return facility;
    }

    private Facility getVilla(String[] propertyOfFacility) {
        Facility facility;
        facility = new Villa(propertyOfFacility[0],
                propertyOfFacility[1],
                Double.parseDouble(propertyOfFacility[2]),
                Double.parseDouble(propertyOfFacility[3]),
                Integer.parseInt(propertyOfFacility[4]),
                propertyOfFacility[5],
                propertyOfFacility[6],
                Integer.parseInt(propertyOfFacility[7]),
                Double.parseDouble(propertyOfFacility[8]));
        return facility;
    }

    private void writeFileFacility(Map<Facility, Integer> facilityAll) {
        List<String> villaString = convertFacilityToString("VL");
        List<String> houseString = convertFacilityToString("HO");
        List<String> roomString = convertFacilityToString("RO");
        WriteFile.writeFile(PATH_VILLA, villaString);
        WriteFile.writeFile(PATH_HOUSE, houseString);
        WriteFile.writeFile(PATH_ROOM, roomString);
    }

    private List<String> convertFacilityToString(String code) {
        List<String> result = new ArrayList<>();
        for(Facility f : facilityListMap.keySet()) {
            if(f.getId().contains(code)) {
                result.add(f.toString());
            }
        }
        return result;
    }

    @Override
    public void add() {
        facilityListMap = readFileFacility(PATH_FACILITY);
        Facility facility = createFacility();
        if (facility == null) {
            return;
        }
        facilityListMap.put(facility, 0);
        writeFileFacility(facilityListMap);
        System.out.println("add facility success!");
        display();
    }

    private Facility createFacility() {
        int choice;
        while (true) {
            showMenuFacility();
            choice = GetService.getNumberInteger("Your choice: ", 1, 3);
            switch (choice) {
                case 1:
                    return createVilla();
                case 2:
                    return createHouse();
                case 3:
                    return createRoom();
                case 4:
                   return null;

            }
        }
    }

    private Facility createRoom() {
        InputFacilityService.inputRoom();
        return new Room(InputFacilityService.id,
                InputFacilityService.name,
                InputFacilityService.usableArea,
                InputFacilityService.rentalCosts,
                InputFacilityService.maximumOfPeople,
                InputFacilityService.rentalStyle,
                InputFacilityService.freeService);
    }

    private Facility createHouse() {
        InputFacilityService.inputHouse();
        return new House(InputFacilityService.id,
                InputFacilityService.name,
                InputFacilityService.usableArea,
                InputFacilityService.rentalCosts,
                InputFacilityService.maximumOfPeople,
                InputFacilityService.rentalStyle,
                InputFacilityService.roomStand,
                InputFacilityService.numberOfFloors);
    }

    private Facility createVilla() {

        InputFacilityService.inputVilla();
        return new Villa(InputFacilityService.id,
                InputFacilityService.name,
                InputFacilityService.usableArea,
                InputFacilityService.rentalCosts,
                InputFacilityService.maximumOfPeople,
                InputFacilityService.rentalStyle,
                InputFacilityService.roomStand,
                InputFacilityService.numberOfFloors,
                InputFacilityService.swimmingArea);
    }

    private void showMenuFacility() {
        System.out.println("1: add villa");
        System.out.println("2: add house");
        System.out.println("3: add room");
        System.out.println("4. back to menu");
    }

    @Override
    public void edit() {
        Facility facility = findFacilityByID();
        if(facility == null) {
            System.out.println("Facility not found!");
        } else {
            editFacility(facility);
            writeFileFacility(facilityListMap);
            System.out.println("Update success!");
        }
    }

    private void editFacility(Facility facility) {
        if(facility instanceof Villa) {
            editVilla(facility);
        } else if(facility instanceof House){
            editHouse(facility);
        } else {
            editRoom(facility);
        }
    }

    private void editRoom(Facility facility) {
        int choice;
        String[] facilityString = facility.toString().split(",,");
        while (true) {
            System.out.println(getAllPropertyOfVilla(facilityString));
            choice = GetService.getNumberInteger("Your chose property you want to update:",
                    0, facilityString.length);
            if(choice == facilityString.length){
                break;
            }
            setPropertyOfRoom(choice, (Room) facility);
        }
    }

    private void setPropertyOfRoom(int choice, Room facility) {
        switch (choice) {
            case 0 -> {
                String id = GetService.getStr("Enter new villa id: ");
                facility.setId(id);
            }
            case 1 -> {
                String name = GetService.getStr("Enter new villa name: ");
                facility.setName(name);
            }
            case 2 -> {
                double usableArea = GetService.getNumberDouble("Enter new usableArea:", 1, 1000);
                facility.setUsableArea(usableArea);
            }
            case 3 -> {
                double rentalCosts = GetService.getNumberDouble("Enter new rental costs: ", 0, 100000000);
                facility.setRentalCosts(rentalCosts);
            }
            case 4 -> {
                int maximumOfPeople = GetService.getNumberInteger("Enter new maximum of people: ", 1, 50);
                facility.setMaximumOfPeople(maximumOfPeople);
            }
            case 5 -> {
                String rentalStyle = GetService.getRentalStyle();
                facility.setRentalStyle(rentalStyle);
            }
            case 6 -> {
                String freeService = GetService.getStr("Enter new free service: ");
                facility.setFreeService(freeService);
            }
        }
    }

    private void editHouse(Facility facility) {
        int choice;
        String[] facilityString = facility.toString().split(",,");
        while (true) {
            System.out.println(getAllPropertyOfVilla(facilityString));
            choice = GetService.getNumberInteger("Your chose property you want to update:",
                    0, facilityString.length);
            if(choice == facilityString.length){
                break;
            }
            setPropertyOfHouse(choice, (House) facility);
        }
    }

    private void setPropertyOfHouse(int choice, House facility) {
        switch (choice) {
            case 0 -> {
                String id = GetService.getStr("Enter new villa id: ");
                facility.setId(id);
            }
            case 1 -> {
                String name = GetService.getStr("Enter new villa name: ");
                facility.setName(name);
            }
            case 2 -> {
                double usableArea = GetService.getNumberDouble("Enter new usableArea:", 1, 1000);
                facility.setUsableArea(usableArea);
            }
            case 3 -> {
                double rentalCosts = GetService.getNumberDouble("Enter new rental costs: ", 0, 100000000);
                facility.setRentalCosts(rentalCosts);
            }
            case 4 -> {
                int maximumOfPeople = GetService.getNumberInteger("Enter new maximum of people: ", 1, 50);
                facility.setMaximumOfPeople(maximumOfPeople);
            }
            case 5 -> {
                String rentalStyle = GetService.getRentalStyle();
                facility.setRentalStyle(rentalStyle);
            }
            case 6 -> {
                String roomStand = GetService.getRomStand();
                facility.setRoomStand(roomStand);
            }
            case 7 -> {
                int numberOfFloors = GetService.getNumberInteger("number of floor: ", 0, 100);
                facility.setNumberOfFloors(numberOfFloors);
            }
        }
    }

    private void editVilla(Facility facility) {
        int choice;
        String[] facilityString = facility.toString().split(",,");
        while (true) {
            System.out.println(getAllPropertyOfVilla(facilityString));
            choice = GetService.getNumberInteger("Your chose property you want to update:",
                    0, facilityString.length);
            if(choice == facilityString.length){
                break;
            }
            setPropertyOfVilla(choice, (Villa) facility);
        }
    }

    private void setPropertyOfVilla(int choice, Villa facility) {
        switch (choice) {
            case 0 -> {
                String id = GetService.getStr("Enter new villa id: ");
                facility.setId(id);
            }
            case 1 -> {
                String name = GetService.getStr("Enter new villa name: ");
                facility.setName(name);
            }
            case 2 -> {
                double usableArea = GetService.getNumberDouble("Enter new usableArea:", 1, 1000);
                facility.setUsableArea(usableArea);
            }
            case 3 -> {
                double rentalCosts = GetService.getNumberDouble("Enter new rental costs: ", 0, 100000000);
                facility.setRentalCosts(rentalCosts);
            }
            case 4 -> {
                int maximumOfPeople = GetService.getNumberInteger("Enter new maximum of people; ", 1, 50);
                facility.setMaximumOfPeople(maximumOfPeople);
            }
            case 5 -> {
                String rentalStyle = GetService.getRentalStyle();
                facility.setRentalStyle(rentalStyle);
            }
            case 6 -> {
                String roomStand = GetService.getRomStand();
                facility.setRoomStand(roomStand);
            }
            case 7 -> {
                int numberOfFloors = GetService.getNumberInteger("number of floor: ", 0, 100);
                facility.setNumberOfFloors(numberOfFloors);
            }
            case 8 -> {
                double swimmingArea = GetService.getNumberDouble("Enter new swimming area: ", 1, 1000);
                facility.setSwimmingArea(swimmingArea);
            }
        }
    }

    private String getAllPropertyOfVilla(String[] propertyOfVilla) {
        StringBuilder result = new StringBuilder();
        String property ;
        int length = propertyOfVilla.length;
        for(int i = 0; i <length; i++) {
            property = String.format("%d. %s%s", i, propertyOfVilla[i],"    ");
            result.append(property);
        }
        result.append(String.format("\n%d. exit",length));
        return result.toString();
    }


    private Facility findFacilityByID() {
        String id = GetService.getStr("Enter id you want to find: ");
        facilityListMap = readFileFacility(PATH_FACILITY);
       for(Facility f : facilityListMap.keySet()) {
           if(f.getId().equals(id)){
               return f;
           }
       }
       return null;
    }
}
