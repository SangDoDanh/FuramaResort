package service.impl;

import models.person.Employee;
import service.IEmployeeService;
import utils.get_set_service.GetService;
import service.input.InputPersonService;
import utils.read_write.ReadFile;
import utils.read_write.WriteFile;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    private static final String PATH_EMPLOYEE = "src/data/employee.csv";
    private static List<Employee> employeeList;

    @Override
    public void add() {
        employeeList = readFileEmployee(PATH_EMPLOYEE);
        Employee e = createEmployee();
        employeeList.add(e);
        System.out.println();
        System.out.println("add success!");
        writeFileEmployee(PATH_EMPLOYEE, employeeList);
    }

    /**
     * Cập nhật lại các thông tin của employee
     */
    @Override
    public void edit() {
        employeeList = readFileEmployee(PATH_EMPLOYEE);
        int employeeIndex;
        Employee e;
        employeeIndex = findIndexEmployeeByID();
        if (employeeIndex > -1) {
            e = employeeList.get(employeeIndex);
            showPropertyEmployee(e);
            writeFileEmployee(PATH_EMPLOYEE, employeeList);
            System.out.println("Update success!");
            display();
        } else {
            System.out.println("Employee not found!");
        }
    }

    /**
     * Tìm kiếm employee theo id
     *
     * @return vị trí của employee trong employeeList
     */
    private int findIndexEmployeeByID() {
        String id;
        id = GetService.getStr("Enter employee id you want to find: ");
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Hiển thị thông tin các thuộc tính của employee
     *
     * @param e employee
     */

    private void showPropertyEmployee(Employee e) {
        int choose;
        String[] propertyEmployee = e.toString().split(",,");
        String propertyEmployeeString;
        while (true) {
            propertyEmployeeString = convertEmployeeToString(propertyEmployee);
            System.out.println(propertyEmployeeString);
            System.out.printf("%d. exit", propertyEmployee.length);
            choose = GetService.getNumberInteger("Your chose property you want to update: ", 0, 10);
            if (choose == 10) {
                break;
            }
            setProperty(e, choose);
        }
    }

    /**
     * Chuyển thuộc tính employee ở dạng mảng sang dạng chuỗi
     *
     * @param propertyEmployee thuộc tính employee dạng mảng
     * @return thuộc tính employee dạng chuỗi
     */
    private String convertEmployeeToString(String[] propertyEmployee) {
        StringBuilder propertyEmployeeString = new StringBuilder();
        for (int i = 0; i < propertyEmployee.length; i++) {
            propertyEmployeeString.append(i + ".[" + propertyEmployee[i] + "]\t");
        }
        return propertyEmployeeString.toString();
    }

    /**
     * nhập những thay đổi của thuộc tính và cập nhật lại employee
     *
     * @param e      employee
     * @param choose vị trí thuộc tính của employee muốn thay đổi
     */
    private void setProperty(Employee e, int choose) {

        switch (choose) {
            case 0:
                String id = GetService.getStr("Enter new ID: ");
                e.setId(id);
                break;
            case 1:
                String name = GetService.getName("Enter new full name: ");
                e.setFullName(name);
                break;
            case 2:
                String dayOfBirth = GetService.getDate("Enter new day of birth: ");
                e.setDayOfBirth(dayOfBirth);
                break;
            case 3:
                String gender = GetService.getGender();
                e.setGender(gender);
                break;
            case 4:
                String identity = GetService.getStr("Enter new identity: ");
                e.setIdentity(identity);
                break;
            case 5:
                String phoneNumber = GetService.getPhoneNumber("Enter new phone number: ");
                e.setNumberPhone(phoneNumber);
                break;
            case 6:
                String email = GetService.getEmail("Enter new email: ");
                e.setEmail(email);
                break;
            case 7:
                String level = GetService.getLevel();
                e.setLevel(level);
                break;
            case 8:
                String position = GetService.getPosition();
                e.setPosition(position);
                break;
            case 9:
                double salary = GetService.getNumberDouble("Enter new salary: ", 0, 15000000);
                e.setSalary(salary);
                break;
        }
    }

    /**
     * Ghi danh sách employeeList xuống file
     *
     * @param path      đường dẫn file
     * @param employees danh sách employee
     */
    private void writeFileEmployee(String path, List<Employee> employees) {
        List<String> employeeString = new ArrayList<>();
        for (Employee e : employees) {
            employeeString.add(e.toString());
        }
        WriteFile.writeFile(path, employeeString);
    }

    private Employee createEmployee() {
        InputPersonService.inputEmployee();
        return new Employee(InputPersonService.id,
                InputPersonService.fullName,
                InputPersonService.dayOfBirth,
                InputPersonService.gender,
                InputPersonService.identity,
                InputPersonService.numberPhone,
                InputPersonService.email,
                InputPersonService.level,
                InputPersonService.position,
                InputPersonService.salary);
    }

    @Override
    public void display() {
        employeeList = readFileEmployee(PATH_EMPLOYEE);
        if (employeeList.size() == 0) {
            System.out.println("Employee List empty!");
        } else {
            showInfo(employeeList);
        }
    }

    private void showInfo(List<Employee> employeeList) {
        System.out.println("EMPLOYEE_LIST");
        System.out.printf("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-12s|%-12s|%-12s|\n",
                "ID", "NAME", "DAY_OF_BIRTH", "GENDER", "IDENTITY",
                "PHONE", "EMAIL", "LEVEL", "POSITION", "SALARY");
        String infoE = "";
        for (Employee e : employeeList) {
            infoE = String.format("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-12s|%-12s|%12.2f|",
                    e.getId(), e.getFullName(), e.getDayOfBirth(), e.getGender(), e.getIdentity(),
                    e.getNumberPhone(), e.getEmail(), e.getLevel(), e.getPosition(), e.getSalary());
            System.out.println(infoE);
        }
    }

    private List<Employee> readFileEmployee(String path) {
        List<Employee> employees = new ArrayList<>();
        List<String> employeesString = ReadFile.readFile(path);
        String[] propertyOfEmployee;
        for (String employee : employeesString) {
            propertyOfEmployee = employee.split(",,");
            employees.add(new Employee(
                    propertyOfEmployee[0],
                    propertyOfEmployee[1],
                    propertyOfEmployee[2],
                    propertyOfEmployee[3],
                    propertyOfEmployee[4],
                    propertyOfEmployee[5],
                    propertyOfEmployee[6],
                    propertyOfEmployee[7],
                    propertyOfEmployee[8],
                    Double.parseDouble(propertyOfEmployee[9])
            ));
        }

        return employees;
    }

    public List<Employee> getEmployeeList() {
        return readFileEmployee(PATH_EMPLOYEE);
    }
}
