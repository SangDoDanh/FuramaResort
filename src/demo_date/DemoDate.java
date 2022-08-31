package demo_date;

import utils.get_set_service.GetService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DemoDate {
    public static void main(String[] args) {
        LocalDate start = GetService.getDate("Nhap vao ngay bat dau: ", LocalDate.now());
        LocalDate end = GetService.getDate("Nhap vao ngay ket thuc: ", start);
//        Booking
        Queue<Booking> bookingQueue = new PriorityQueue<>(new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                return 0;
            }
        });
        bookingQueue.offer(new Booking(start, end, "B001"));
        for(Booking b : bookingQueue) {
            System.out.println(b);
        }
    }
}
