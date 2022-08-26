package controllers;

import service.ICustomerService;
import service.impl.CustomerService;
import utils.get_set_service.GetService;

public class CustomerController {
    private static final ICustomerService I_CUSTOMER_SERVICE = new CustomerService();
    private static final String YOUR_CHOOSE = "Your choose: ";

    public void displayMainMenu() {
        int choose;
        while (true) {
            showSubMenu("customer");
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 4);
            switch (choose) {
                case 1:
                    I_CUSTOMER_SERVICE.display();
                    break;
                case 2:
                    I_CUSTOMER_SERVICE.add();
                    break;
                case 3:
                    I_CUSTOMER_SERVICE.edit();
                    break;
                case 4:
                    return;
            }
        }
    }
    private void showSubMenu(String subName) {
        System.out.println("1. Display list " + subName);
        System.out.println("2. Add new " + subName);
        System.out.println("3. Edit  " + subName);
        System.out.println("4. return main menu ");
    }

}
