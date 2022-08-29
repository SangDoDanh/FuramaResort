package service.impl.get;

import models.Booking;
import service.i_get.IGetBookingPropertyService;
import service.impl.BookingService;
import utils.get_set_service.GetService;
import java.util.Queue;

public class GetBookingPropertyService implements IGetBookingPropertyService {
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
