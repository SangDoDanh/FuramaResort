package controllers;

import service.IFacilityService;
import service.impl.FacilityService;
import utils.get_set_service.GetService;

public class FacilityController {
    private final IFacilityService I_FACILITY_SERVICE = new FacilityService();
    private static final String YOUR_CHOOSE = "Your choose: ";

    public void displayMainMenu() {
        int choose;
        while (true) {
            showSubMenu("facility");
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
    private void showSubMenu(String subName) {
        System.out.println("---FACILITY MANAGEMENT---");
        System.out.println("1. Display list " + subName);
        System.out.println("2. Add new " + subName);
        System.out.println("3. Edit  " + subName);
        System.out.println("4. return main menu ");
    }
}
