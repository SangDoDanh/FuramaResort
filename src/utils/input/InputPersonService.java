package utils.input;

import utils.get_set_service.GetService;

import java.util.Scanner;

public class InputPersonService {
    private static Scanner sc = new Scanner(System.in);
    public static String id;
    public static String fullName;
    public static String dayOfBirth;
    public static String gender;
    public static String identity;
    public static String numberPhone;
    public static String email;
    public static String position;
    public static String level;
    public static double salary;
    public static String rank;
    public static String address;
    public static void inputPerson() {
        id = GetService.getStr("Enter ID: ");
        fullName = GetService.getName("Enter Name: ");
        dayOfBirth = GetService.getDate("Enter day of birth: ");
        gender = GetService.getGender();
        identity = GetService.getStr("Enter identity: ");
        numberPhone = GetService.getPhoneNumber("Enter phone number: ");
        email = GetService.getEmail("Enter email: ");

    }
    public static void inputEmployee() {
        inputPerson();
        position = GetService.getPosition();
        level = GetService.getLevel();
        salary = GetService.getNumberDouble("Enter salary: ", 0,100000000);
    }
    public static void inputCustomer() {
        inputPerson();
        rank = GetService.getRank();
        address = GetService.getStr("Enter address: ");
    }
}
