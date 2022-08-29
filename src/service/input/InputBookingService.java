package service.input;

import service.impl.get.GetBookingPropertyService;
import service.impl.get.GetCustomerPropertyService;
import service.impl.get.GetFacilityPropertyService;
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
        GetBookingPropertyService getBookingP = new GetBookingPropertyService();
        id = getBookingP.getBookingId();
        startDay = GetService.getDate("Enter start day by format dd/mm/yyyy: ", LocalDate.now());
        endDay = GetService.getDate("Enter end day by format dd/mm/yyyy: ", startDay);
        customerID = GetCustomerPropertyService.getChoiceCustomerID();
        serviceName = GetFacilityPropertyService.getFacilityName();
        typeOfService = GetFacilityPropertyService.getRentalStyle(serviceName);
    }
}
