package controllers;

import utils.get_set_service.GetService;

public class PromotionController {
    private static final String YOUR_CHOOSE = "Your choose: ";
    public void displayMainMenu() {
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
    private void showPromotionMenu() {
        System.out.println("---PROMOTION MANAGEMENT---");
        System.out.println("1. Display list customer use service");
        System.out.println("2. Display list customer get voucher");
        System.out.println("3. return main menu");
    }
}
