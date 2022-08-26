package controllers;

import service.IEmployeeService;
import service.impl.EmployeeService;
import utils.get_set_service.GetService;

public class FuramaController {
    private static final String YOUR_CHOOSE = "Your choose: ";
    private static final IEmployeeService I_EMPLOYEE_SERVICE = new EmployeeService();
    private static final CustomerController CUSTOMER_CONTROLLER = new CustomerController();
    private static final EmployeeController EMPLOYEE_CONTROLLER = new EmployeeController();
    private static final FacilityController FACILITY_CONTROLLER = new FacilityController();
    public void displayMainMenu() {
        int choose;
        while (true) {
            showMainMenu();
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 6);
            switch (choose) {
                case 1:
                    EMPLOYEE_CONTROLLER.displayMainMenu();
                    break;
                case 2:
                    CUSTOMER_CONTROLLER.displayMainMenu();
                    break;
                case 3:
                    FACILITY_CONTROLLER.displayMainMenu();
                    break;
                case 4:
                    bookingMenu();
                    break;
                case 5:
                    promotionMenu();
                    break;
                case 6:
                    return;
            }
        }

    }

    private void promotionMenu() {
        int choose;
        while (true) {
            showPromotionMenu();
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 3);
            switch (choose) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    return;
            }
        }
    }

    private void bookingMenu() {
        int choose;
        while (true) {
            showBookingMenu();
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 6);
            switch (choose) {
                case 1:
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

    private void showMainMenu() {
        System.out.println("1. Employee Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Facility Management");
        System.out.println("4. Booking Management");
        System.out.println("5. Promotion Management");
        System.out.println("6. Exit");
    }

    private void showSubMenu(String subName) {
        System.out.println("1. Display list " + subName);
        System.out.println("2. Add new " + subName);
        System.out.println("3. Edit  " + subName);
        System.out.println("4. return main menu ");
    }

    private void showBookingMenu() {
        System.out.println("1. Add new booking");
        System.out.println("2. Display list booking");
        System.out.println("3. Create new contacts");
        System.out.println("4. Display list contacts");
        System.out.println("5. Edit contacts");
        System.out.println("6. Return main menu");

    }

    private void showPromotionMenu() {
        System.out.println("1. Display list customer use service");
        System.out.println("2. Display list customer get voucher");
        System.out.println("3. return main menu");
    }


}
