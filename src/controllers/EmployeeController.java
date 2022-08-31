package controllers;

import service.IEmployeeService;
import service.impl.EmployeeService;
import utils.get_set_service.GetService;

public class EmployeeController {
    private static final IEmployeeService I_EMPLOYEE_SERVICE = EmployeeService.getInstance();
    private static final String YOUR_CHOOSE = "Your choose: ";

    public void displayMainMenu() {
        int choose;
        while (true) {
            showSubMenu();
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 4);
            switch (choose) {
                case 1:
                    I_EMPLOYEE_SERVICE.display();
                    break;
                case 2:
                    I_EMPLOYEE_SERVICE.add();
                    break;
                case 3:
                    I_EMPLOYEE_SERVICE.edit();
                    break;
                case 4:
                    return;
            }
        }
    }
    private void showSubMenu() {
        System.out.println("---EMPLOYEE MANAGEMENT---");
        System.out.println("1. Display list employee");
        System.out.println("2. Add new employee");
        System.out.println("3. Edit employee");
        System.out.println("4. return main menu ");
    }
}
