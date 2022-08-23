package service.impl;

import models.Employee;
import service.IEmployeeService;
import utils.read_write.ReadFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    private static String PATH_EMPLOYEE = "src/data/ employee.csv";
    private static List<Employee> employeeList;
    @Override
    public void display() {
        employeeList = readFileEmployee(PATH_EMPLOYEE);
        if(employeeList.size() == 0) {
            System.out.println("Employee List empty!");
        } else {
           showInfo(employeeList);
        }
    }

    @Override
    public void add() {
        Employee e = createEmployee();
        employeeList = readFileEmployee(PATH_EMPLOYEE);
        employeeList.add(e);
        System.out.println("add success!");
        writeFileEmployee();
    }

    private void showInfo(List<Employee> employeeList) {
        System.out.printf("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-10s|%-12s|%-12s|\n",
                "ID", "NAME", "DAY_OF_BIRTH", "GENDER","IDENTITY",
                "PHONE","EMAIL", "LEVEL", "POSITION", "SALARY");
        String infoE = "";
        for(Employee e : employeeList) {
            infoE = String.format("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-10s|%-12s|%12.2f|",
                    e.getId(), e.getFullName(), e.getDayOfBirth(), e.getGender(),e.getIdentity(),
                    e.getNumberPhone(),e.getEmail(), e.getLevel(), e.getPosition(), e.getSalary());
            System.out.println(infoE);
        }
    }

    private List<Employee> readFileEmployee(String path) {
        List<Employee> employees = new ArrayList<>();
        List<String> employeesString = ReadFile.readFile(path);
        String[] propertyOfEmployee;
            for(String employee : employeesString) {
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
}
