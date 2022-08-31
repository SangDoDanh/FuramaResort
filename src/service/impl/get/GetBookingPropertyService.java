package service.impl.get;

import models.Booking;
import models.facility.Facility;
import service.i_get.IGetBookingPropertyService;
import service.impl.BookingService;
import utils.get_set_service.GetService;

import java.util.Queue;

public class GetBookingPropertyService implements IGetBookingPropertyService {
    public static double calTotalPay(Booking booking) {
        final int DAY_OF_MONTH = 30;
        final int DAY_OF_YEAR = 365;
        long fromDay = booking.getStartDay().toEpochDay();
        long toDay = booking.getStartDay().toEpochDay();
        int numberOFDay = (int)Math.abs(fromDay - toDay);
        Facility facility = GetFacilityPropertyService.getFacility(booking.getServiceName());
        if(facility == null) {
            return  1;
        }
        if(facility.getRentalStyle().equalsIgnoreCase("month")) {
            return numberOFDay * (facility.getRentalCosts()/DAY_OF_MONTH);
        } else if(facility.getRentalStyle().equalsIgnoreCase("day")) {
            return numberOFDay * (facility.getRentalCosts());
        }
        return numberOFDay * (facility.getRentalCosts()/DAY_OF_YEAR);
    }

    @Override
    public String getBookingId() {
         String bookingId;
         final String BOOING_ID_REGEX = "BO\\d{3}";
         while(true){
             bookingId = GetService.getStr("Enter booking id by format [BOxxx] x is number: ");
             if(!bookingId.matches(BOOING_ID_REGEX)) {
                 System.out.println("Example: BO001, BO002, BO003, ...");
             } else if(!checkBooingId(bookingId)) {
                 System.out.printf("[%s] used!\n", bookingId);
             } else {
                 return bookingId;
             }
        }
    }
    private boolean checkBooingId(String bookingId) {
        Queue<Booking> bookings = new BookingService().getBookingList();
        for(Booking b : bookings) {
            if(b.getId().equalsIgnoreCase(bookingId)) {
                return false;
            }
        }
        return true;
    }

    public static Booking getChoiceBookingByID() {
        BookingService bookingService = new BookingService();
        Queue<Booking> bookings;
        String newBookingID;
        bookings = bookingService.getBookings();
        while (true) {
            bookingService.display();
            newBookingID = GetService.getStr("Enter new Booking ID: ");
            for(Booking b : bookings) {
                if(b.getId().equalsIgnoreCase(newBookingID)) {
                    return b;
                }
            }
        }
    }


}
