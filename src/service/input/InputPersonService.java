package service.input;

import service.i_get.IGetCustomerPropertyService;
import service.i_get.IGetEmployeePropertyService;
import service.impl.get.GetCustomerPropertyService;
import service.impl.get.GetEmployeePropertyService;
import utils.get_set_service.GetService;

public class InputPersonService {
    private static final String E = "Employee";
    private static final String C = "Customer";
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

    public static void inputPerson(String person) {
        if (person.equals(E)) {
            id = I_GET_EMPLOYEE_PROPERTY.getEmployeeID();
        } else {
            id = I_GET_CUSTOMER_PROPERTY.getCustomerID();
        }
        fullName = GetService.getName("Enter Name: ");
        dayOfBirth = GetService.getDate("Enter day of birth: ", 18);
        gender = GetService.getGender();
        identity = GetService.getStr("Enter identity: ");
        numberPhone = GetService.getPhoneNumber("Enter phone number: ");
        email = GetService.getEmail("Enter email: ");

    }

    public static void inputEmployee() {
        inputPerson(E);
        position = GetEmployeePropertyService.getPosition();
        level = GetEmployeePropertyService.getLevel();
        salary = GetService.getNumberDouble("Enter salary: ", 0, 100000000);
    }

    public static void inputCustomer() {
        inputPerson(C);
        rank = GetCustomerPropertyService.getRank();
        address = GetService.getStr("Enter address: ");
    }
}
