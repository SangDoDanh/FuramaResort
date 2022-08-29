package service.impl.get;

import models.person.Employee;
import service.i_get.IGetEmployeePropertyService;
import service.impl.EmployeeService;
import utils.get_set_service.GetService;

import java.util.List;

public class GetEmployeePropertyService implements IGetEmployeePropertyService {
    EmployeeService employeeService = new EmployeeService();
    private static final String EMPLOYEE_ID_REGEX = "E\\d{3,}";
    @Override
    public String getEmployeeID() {
        String employeeID;
        while (true) {
            employeeID = GetService.getStr("Enter employee id in the format [Exxx]: ");
            if(employeeID.matches(EMPLOYEE_ID_REGEX) && checkID(employeeID)) {
                return employeeID;
            } else {
                System.out.println("Example : E001, E002, E003...");
            }
        }

    }

    private boolean checkID(String employeeID) {
        List<Employee> employees = employeeService.getEmployeeList();
        if(employees.size() == 0) {
            return true;
        }
        for(Employee e : employees) {
            if(e.getId().equalsIgnoreCase(employeeID)) {
                return false;
            }
        }
        return true;
    }
    public static String getLevel() {
        String[] positions = {"University", "High school", "Nothing"};
        int choose;
        System.out.println("1. University \t2. High school \t3. Nothing");
        choose = GetService.getNumberInteger("Your choose: ", 1, 3);
        return positions[choose - 1];
    }

    public static String getPosition() {
        String[] positions = {"Worker", "Manager", "President", "Other"};
        int choose;
        System.out.println("1. Worker \t2. Manager \t3. President \t4. Other");
        choose = GetService.getNumberInteger("Your choose: ", 1, 4);
        return positions[choose - 1];
    }

}
