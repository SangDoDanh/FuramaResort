package service.impl;

import models.Booking;
import models.person.Customer;
import service.IPromotionService;
import utils.get_set_service.GetService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class PromotionService implements IPromotionService {
    private static PromotionService instance;

    public PromotionService() {
    }

    public static PromotionService getInstance() {
        if(instance == null) {
            instance = new PromotionService();
        }
        return instance;
    }
    private static final BookingService BOOKING_SERVICE = new BookingService();
    private static final CustomerService CUSTOMER_SERVICE = new CustomerService();


    @Override
    public void displayCustomerUsed() {
        int year = GetService.getNumberInteger("Enter year: ", 1, LocalDate.now().getYear());
        Map<Customer, Integer> customersUse = getCustomerUserWithYear(year);
        if(customersUse.size() == 0) {
            System.out.printf("Customer user is empty! year: [%d]\n", year);
        } else {
            for(Customer c : customersUse.keySet()) {
                System.out.println(c);
            }
        }
    }

    public void displayCustomerVoucher() {
        Map<String, Integer> vouchers = createVoucher();
        int month = LocalDate.now().getMonthValue();
        Stack<Customer> customers = getCustomerUserWithMonth(month, LocalDate.now().getYear());
        if(customers.size() == 0) {
            System.out.printf("Customer is empty! Month[%d]\n", month);
        } else {
            String voucher;
            for (Customer c : customers) {
                voucher = getVoucher(vouchers);
                System.out.printf("customer: %s    voucher: %s\n",c.getFullName(),voucher);
            }
        }
    }

    private String getVoucher(Map<String, Integer> vouchers) {
        String result = "";
        for(Map.Entry<String, Integer> v : vouchers.entrySet()) {
            if(v.getValue() > 0) {
                result = v.getKey();
                v.setValue(v.getValue() - 1);
                return result;
            }
        }
        return result;
    }


    private Stack<Customer> getCustomerUserWithMonth(int monthValue, int year) {
        Queue<Booking> bookings = BOOKING_SERVICE.getBookings();
        Stack<Customer> customersID = new Stack<>();
        for(Booking b : bookings) {
            if(b.getStartDay().getMonthValue() == monthValue && b.getStartDay().getYear() == year) {
                customersID.push(CUSTOMER_SERVICE.findCustomerBYID(b.getCustomerID()));
            }
        }
        return customersID;
    }

    private Map<String, Integer> createVoucher() {
        Map<String, Integer> voucherList = new TreeMap<>();
        int numberOfVoucher10 = GetService.getNumberInteger("Enter number of voucher 10%: ", 0,100);
        int numberOfVoucher20 = GetService.getNumberInteger("Enter number of voucher 20%: ", 0,100);
        int numberOfVoucher50 = GetService.getNumberInteger("Enter number of voucher 50%: ", 0,100);
        voucherList.put("Voucher10%", numberOfVoucher10);
        voucherList.put("Voucher20%", numberOfVoucher20);
        voucherList.put("Voucher50%", numberOfVoucher50);
        return voucherList;
    }

    private Map<Customer, Integer> getCustomerUserWithYear(int year) {
        Queue<Booking> bookings = BOOKING_SERVICE.getBookings();
        Map<Customer, Integer> customersUse = new TreeMap<>((o1, o2) -> 0);
        for(Booking b : bookings) {
            if(b.getStartDay().getYear() == year) {
                customersUse.put(CUSTOMER_SERVICE.findCustomerBYID(b.getCustomerID()), 0);
            }
        }
        return customersUse;
    }
}
