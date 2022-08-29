package test_date;

import utils.get_set_service.GetService;

public class TestDate {
    public static void main(String[] args) {
        String date = GetService.getDate("Enter day of birth: ", 18);
        System.out.println(date);
    }
}
