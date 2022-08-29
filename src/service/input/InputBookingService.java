package service.input;

import service.impl.get.GetBookingPropertyService;
import utils.get_set_service.GetService;

import java.time.LocalDate;

public class InputBookingService {
    public static String id;
    public static LocalDate startDay;
    public static LocalDate endDay;
    public static String customerID;
    public static String serviceName;
    public static String typeOfService;

    public static void inputBooking() {
        id = new GetBookingPropertyService().getBookingId();
        id = GetService.getStr("Enter booking id: ");
        startDay = GetService.getLocalDate("Enter start day: ");
        endDay = GetService.getLocalDate("Enter end day: ");
        customerID = GetService.getCustomerID();
        serviceName = GetService.getFacilityName();
        typeOfService = GetService.getRentalStyle(serviceName);
    }
}
