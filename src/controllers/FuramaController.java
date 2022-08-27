package controllers;

import utils.get_set_service.GetService;

public class FuramaController {
    private static final String YOUR_CHOOSE = "Your choose: ";
    private static final CustomerController CUSTOMER_CONTROLLER = new CustomerController();
    private static final EmployeeController EMPLOYEE_CONTROLLER = new EmployeeController();
    private static final FacilityController FACILITY_CONTROLLER = new FacilityController();
    private static final BookingController BOOKING_CONTROLLER = new BookingController();
    private static final PromotionController PROMOTION_CONTROLLER = new PromotionController();

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
                    BOOKING_CONTROLLER.dislayMainMenu();
                    break;
                case 5:
                    PROMOTION_CONTROLLER.displayMainMenu();
                    break;
                case 6:
                    return;
            }
        }
    }
    private void showMainMenu() {
        System.out.println("---FURUMA MANAGEMENT---");
        System.out.println("1. Employee Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Facility Management");
        System.out.println("4. Booking Management");
        System.out.println("5. Promotion Management");
        System.out.println("6. Exit");
    }
}
