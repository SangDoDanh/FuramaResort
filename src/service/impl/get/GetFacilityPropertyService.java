package service.impl.get;

import models.facility.Facility;
import service.IFacilityService;
import service.i_get.IGetFacilityPropertyService;
import service.impl.FacilityService;
import utils.get_set_service.GetService;

import java.util.Map;

public class GetFacilityPropertyService implements IGetFacilityPropertyService {
    public String getFacilityId(String facilityId) {
        if(facilityId.equalsIgnoreCase("villa")) {
            return getVillaId();
        } else if(facilityId.equalsIgnoreCase("house")){
            return getHouseID();
        }
        return getRoomId();
    }

    private String getRoomId() {
        final String VILLA_ID_REX = "^RO-\\d{3}$";
        String roomId ;
        while (true) {
            roomId = GetService.getStr("Enter room id by format [ROxxx]: ");
            if(!roomId.matches(VILLA_ID_REX)) {
                System.out.println("Example: RO001, RO002, RO003, ...");
            } else if(!checkFacilityId(roomId)) {
                System.out.printf("[%s] used!\n", roomId);
            } else {
                return roomId;
            }
        }
    }

    private String getHouseID() {
        final String VILLA_ID_REX = "^HO-\\d{3}$";
        String houseId ;
        while (true) {
            houseId = GetService.getStr("Enter house id by format [HOxxx]: ");
            if(!houseId.matches(VILLA_ID_REX)) {
                System.out.println("Example: HO001, HO002, HO003, ...");
            } else if(!checkFacilityId(houseId)) {
                System.out.printf("[%s] used!\n", houseId);
            } else {
                return houseId;
            }
        }
    }

    @Override
    public String getVillaId() {
        final String VILLA_ID_REX = "^VL-\\d{3}$";
        String villaId ;
        while (true) {
            villaId = GetService.getStr("Enter villa id by format [VLxxx]: ");
            if(!villaId.matches(VILLA_ID_REX)) {
                System.out.println("Example: VL001, VL002, VL003, ...");
            } else if(!checkFacilityId(villaId)) {
                System.out.printf("[%s] used!\n", villaId);
            } else {
                return villaId;
            }
        }
    }

    private boolean checkFacilityId(String facilityId) {
        Map<Facility, Integer> facilityList = new FacilityService().getFacilityList();
        for(Facility f : facilityList.keySet()) {
            if(f.getId().equalsIgnoreCase(facilityId)){
                return false;
            }
        }
        return true;
    }
    public static String getFacilityName() {
        FacilityService facilityService = new FacilityService();
        facilityService.display();
        Facility facility;
        while (true) {
            facility = facilityService.findFacilityByID();
            if(facility != null) {
                return facility.getId();
            }
        }
    }
    public static String getRentalStyle(String facilityID) {
        IFacilityService iFacilityService = new FacilityService();
        Facility facility = iFacilityService.finFacilityByID(facilityID);
        return facility.getRentalStyle();
    }
    public static String getRentalStyle() {
        String[] rentalStyles = {"Year", "Month", "Day", "hours"};
        int choose;
        System.out.println("1. Year \t2. Month \t3. Day \t4. hours");
        choose = GetService.getNumberInteger("Your choose: ", 1, 4);
        return rentalStyles[choose - 1];
    }
    public static String getRomStand() {
        String[] romStand = {"President", "vip", "Normal"};
        int choose;
        System.out.println("1. President \t2. vip \t3. Normal");
        choose = GetService.getNumberInteger("Your choose: ", 1, 3);
        return romStand[choose - 1];
    }
}
