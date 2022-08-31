package controllers;

import service.IFacilityService;
import service.impl.FacilityService;
import utils.get_set_service.GetService;

public class FacilityController {
    private final IFacilityService I_FACILITY_SERVICE = FacilityService.getInstance();
    private static final String YOUR_CHOOSE = "Your choose: ";

    public void displayMainMenu() {
        int choose;
        while (true) {
            showSubMenu();
            choose = GetService.getNumberInteger(YOUR_CHOOSE, 1, 4);
            switch (choose) {
                case 1:
                    I_FACILITY_SERVICE.display();
                    break;
                case 2:
                    I_FACILITY_SERVICE.add();
                    break;
                case 3:
                    I_FACILITY_SERVICE.edit();
                    break;
                case 4:
                    return;
            }
        }
    }

    private void showSubMenu() {
        System.out.println("---FACILITY MANAGEMENT---");
        System.out.println("1. Display list facility");
        System.out.println("2. Add new facility");
        System.out.println("3. Edit facility");
        System.out.println("4. return main menu ");
    }
}
