package service.impl;

import models.Booking;
import service.IBookingService;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BookingService implements IBookingService {
    private static final String PATH_BOOKING = "src/data/booking.csv";
    Queue<Booking> bookingQueue;
    @Override
    public void display() {

    }

    @Override
    public void add() {
        bookingQueue = readFileBooking(PATH_BOOKING);
    }

    private Queue<Booking> readFileBooking(String pathBooking) {
        Queue<Booking> bookings = new PriorityQueue<>(new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                if(o1.getStartDay().getYear() > o2.getStartDay().getYear()) {
                    return 1;
                } else if (o1.getStartDay().getYear() < o2.getStartDay().getYear()){
                    return -1;
                } else {
                    if(o1.getStartDay().getMonthValue() > o2.getStartDay().getMonthValue()) {
                        return 1;
                    } else if(o1.getStartDay().getMonthValue() < o2.getStartDay().getMonthValue()){
                        return -1;
                    } else {
                        if(o1.getStartDay().)
                    }
                }
            }
        });

    }
}
