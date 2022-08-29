package service.input;

import service.IGetCustomerPropertyService;
import service.IGetEmployeePropertyService;
import service.impl.get.GetCustomerPropertyService;
import service.impl.get.GetEmployeePropertyService;
import utils.get_set_service.GetService;

import java.util.Scanner;

public class InputPersonService {
    private static Scanner sc = new Scanner(System.in);
    private static final IGetEmployeePropertyService I_GET_EMPLOYEE_PROPERTY = new GetEmployeePropertyService();
    private static final IGetCustomerPropertyService I_GET_CUSTOMER_PROPERTY = new GetCustomerPropertyService();
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
        fullName = GetService.getName("Enter Name: ");
        dayOfBirth = GetService.getDate("Enter day of birth: ",18);
        gender = GetService.getGender();
        identity = GetService.getStr("Enter identity: ");
        numberPhone = GetService.getPhoneNumber("Enter phone number: ");
        email = GetService.getEmail("Enter email: ");

    }
    public static void inputEmployee() {
        id = I_GET_EMPLOYEE_PROPERTY.getEmployeeID();
        inputPerson();
        position = GetService.getPosition();
        level = GetService.getLevel();
        salary = GetService.getNumberDouble("Enter salary: ", 0,100000000);
    }
    public static void inputCustomer() {
        id = I_GET_CUSTOMER_PROPERTY.getCustomerID();
        inputPerson();
        rank = GetService.getRank();
        address = GetService.getStr("Enter address: ");
    }
}
