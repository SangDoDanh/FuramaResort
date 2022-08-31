package service.impl;

import models.Booking;
import service.IBookingService;
import service.IContactService;
import service.input.InputBookingService;
import utils.read_write.ReadFile;
import utils.read_write.WriteFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingService implements IBookingService {
    private static BookingService instance;

    public BookingService() {
    }
    public synchronized static BookingService getInstance() {
        if(instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    private static final String PATH_BOOKING = "src/data/booking.csv";
    /**
     * bookingsToContract: Danh sách booking có thể lập hợp đồng
     * bookingsToContracted: danh sách booking đã được lập hợp đồng
     */
    private static final Queue<Booking> bookingsToContract = new PriorityQueue<>(Comparator.comparing(Booking::getStartDay));
    public static List<String> bookingsToContracted = new ArrayList<>();
    public static Queue<Booking> bookingQueue;

    public Queue<Booking> getBookingList() {
        return readFileBooking();
    }

    @Override
    public void display() {
        System.out.println("---BOOKING LIST---");
        bookingQueue = readFileBooking();
        for (Booking b : bookingQueue) {
            System.out.println(b);
        }
    }

    /**
     * Thêm mới Booking vào danh sách booking sử dụng Queue
     * hiển thị danh sách booking sau khi thêm mới thành công
     */
    @Override
    public void add() {
        bookingQueue = readFileBooking();
        Booking booking = createBooking();
        new FacilityService().updateNumberOfUsed(booking.getServiceName());
        bookingQueue.offer(booking);
        writeFileBooking(bookingQueue);
        System.out.println("Add booking success!");
        display();
    }

    /**
     * tạo mới booking
     *
     * @return Booking
     */

    private Booking createBooking() {
        InputBookingService.inputBooking();
        return new Booking(InputBookingService.id,
                InputBookingService.startDay,
                InputBookingService.endDay,
                InputBookingService.customerID,
                InputBookingService.serviceName,
                InputBookingService.typeOfService);
    }

    private Queue<Booking> readFileBooking() {
        Queue<Booking> bookings = new PriorityQueue<>((o1, o2) -> {
            if(o1.getStartDay().compareTo(o2.getStartDay()) == 0) {
                return o1.getEndDay().compareTo(o2.getEndDay());
            }
            return o1.getStartDay().compareTo(o2.getStartDay());
        });
        List<String> bookingString;
        bookingString = ReadFile.readFile(PATH_BOOKING);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String[] propertyOfBooking;
        for (String bString : bookingString) {
            propertyOfBooking = bString.split(",,");
            bookings.offer(new Booking(propertyOfBooking[0],
                    LocalDate.parse(propertyOfBooking[1], formatter),
                    LocalDate.parse(propertyOfBooking[2], formatter),
                    propertyOfBooking[3],
                    propertyOfBooking[4],
                    propertyOfBooking[5]));
        }
        return bookings;
    }

    private void writeFileBooking(Queue<Booking> bookings) {
        List<String> bookingsString = new ArrayList<>();
        for (Booking b : bookings) {
            bookingsString.add(b.toString());
        }
        WriteFile.writeFile(PATH_BOOKING, bookingsString);
    }

    /**
     * lấy ra danh sách các booking có thể lập hợp đồng
     *
     * @return Queue<Booking>
     */
    private Queue<Booking> getBookingsToContract() {
        bookingQueue = readFileBooking();
        for (Booking b : bookingQueue) {
            if (!checkBookingTOContracted(b.getId()) && checkBookingToContract(b.getServiceName())) {
                bookingsToContract.add(b);
            }
        }
        return bookingsToContract;
    }

    /**
     * Kiểm tra xem booking có thể lập được hợp đồng không,
     * Thuê villa hoặc thuê house thì phải lập hợp đồng
     * thuê room không cần lập hợp đồng
     *
     * @param serviceName mã dịch vụ
     * @return true -> dịch vụ là villa hoặc house, false -> dịch vụ là room
     */
    private boolean checkBookingToContract(String serviceName) {
        return !serviceName.equalsIgnoreCase("room");
    }

    /**
     * Kiểm tra xem booking đã được lập hợp đồng chưa
     *
     * @param id mã booking
     * @return true nếu trong danh sách các booking đã được lập hợp đồng có id này
     */
    private boolean checkBookingTOContracted(String id) {
        if (bookingsToContracted.size() == 0) {
            return false;
        } else {
            for (String bookingID : bookingsToContracted) {
                if (bookingID.equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tạo hợp đồng cho boooking
     */
    @Override
    public void createNewContracts() {
        Queue<Booking> contracts = getBookingsToContract();
        if (contracts.size() == 0) {
            System.out.println("Booking is empty!");
        } else {
            IContactService I_CONTRACT_SERVICE = new ContractService();
            I_CONTRACT_SERVICE.createContract(contracts.poll());
        }
    }

    public Queue<Booking> getBookings() {
        bookingQueue = readFileBooking();
        return bookingQueue;
    }
}
