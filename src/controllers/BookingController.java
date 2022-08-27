package controllers;

import service.IBookingService;
import service.impl.BookingService;
import utils.get_set_service.GetService;

public class BookingController {
    private static final String YOUR_CHOOSE = "Your choose: ";
    private static final IBookingService I_BOOKING_SERVICE = new BookingService();
    public void dislayMainMenu() {
        int choose;
        while (true) {
            showBookingMenu();
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 6);
            switch (choose) {
                case 1:
                    I_BOOKING_SERVICE.add();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    return;
            }
        }
    }
    private void showBookingMenu() {
        System.out.println("---BOOKING MANAGEMENT---");
        System.out.println("1. Add new booking");
        System.out.println("2. Display list booking");
        System.out.println("3. Create new contacts");
        System.out.println("4. Display list contacts");
        System.out.println("5. Edit contacts");
        System.out.println("6. Return main menu");

    }
}
