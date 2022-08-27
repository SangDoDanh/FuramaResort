package service.impl;

import models.person.Customer;
import service.ICustomerService;
import utils.get_set_service.GetService;
import utils.input.InputPersonService;
import utils.read_write.ReadFile;
import utils.read_write.WriteFile;

import java.util.LinkedList;
import java.util.List;

public class CustomerService implements ICustomerService {
    static final String PATH_CUSTOMER = "src/data/customer.csv";
    private static List<Customer> customerList;


    private List<Customer> readFileCustomer(String path) {
        List<Customer> customers = new LinkedList<>();
        List<String> customersString = ReadFile.readFile(path);
        String[] propertyOfCustomer;
        for (String customerString : customersString) {
            propertyOfCustomer = customerString.split(",,");
            customers.add(new Customer(propertyOfCustomer[0],
                    propertyOfCustomer[1],
                    propertyOfCustomer[2],
                    propertyOfCustomer[3],
                    propertyOfCustomer[4],
                    propertyOfCustomer[5],
                    propertyOfCustomer[6],
                    propertyOfCustomer[7],
                    propertyOfCustomer[8]));
        }
        return customers;
    }
    @Override
    public void display() {
        customerList = readFileCustomer(PATH_CUSTOMER);
        if(customerList.size() == 0) {
            System.out.println("Customer list is empty!");
        } else {
           showInfoCustomerList();
        }
    }

    /**
     * chỉnh sửa thông tin của một customer
     * tìm kiếm customer muốn thay đổi
     * chọn thuộc tính muốn thay đổi
     * nhập thông tin thay đổi cho thuộc tính đó
     * lưu lại và hiển thị
     */
    public void edit() {
        customerList = readFileCustomer(PATH_CUSTOMER);
        int customerIndex;
        Customer c;
        customerIndex = findIndexCustomerByID();
        if (customerIndex > -1) {
            c = customerList.get(customerIndex);
            setPropertyCustomer(c);
            writeFileCustomer(PATH_CUSTOMER, customerList);
            System.out.println("Update success!");
            display();
        } else {
            System.out.println("Employee not found!");
        }
    }

    /**
     * Chọn thuộc tính muốn thay đổi
     * @param c Customer muốn thay đổi
     */
    private void setPropertyCustomer(Customer c) {
        int choose;
        String[] propertyCustomer = c.toString().split(",,");
        String propertyCustomerString = convertCustomerToString(propertyCustomer);
        while (true) {
            System.out.println(propertyCustomerString);
            System.out.println("9. exit");
            choose = GetService.getNumberInteger("Your chose property you want to update: ", 0, 10);
            if(choose == 9) {
                break;
            }
            setProperty(c, choose);
        }
    }

    /**
     * Nhập những thay đổi của customer
     * @param c customer muốn thay đổi
     * @param choose vị trí thuộc tính của customer
     */
    private void setProperty(Customer c, int choose) {

        switch (choose) {
            case 0:
                String id = GetService.getStr("Enter new ID: ");
                c.setId(id);
                break;
            case 1:
                String name = GetService.getName("Enter new full name: ");
                c.setFullName(name);
                break;
            case 2:
                String dayOfBirth = GetService.getDate("Enter new day of birth: ");
                c.setDayOfBirth(dayOfBirth);
                break;
            case 3:
                String gender = GetService.getGender();
                c.setGender(gender);
                break;
            case 4:
                String identity = GetService.getStr("Enter new identity: ");
                c.setIdentity(identity);
                break;
            case 5:
                String phoneNumber = GetService.getPhoneNumber("Enter new phone number: ");
                c.setNumberPhone(phoneNumber);
                break;
            case 6:
                String email = GetService.getEmail("Enter new email: ");
                c.setEmail(email);
                break;
            case 7:
                String rank = GetService.getRank();
                c.setRank(rank);
                break;
            case 8:
                String address = GetService.getStr("Enter new address:");
                c.setAddress(address);
                break;
        }
    }

    /**
     * chuyển đổi customer sang một chuỗi
     * @param propertyCustomer mảng các thuộc tính của customer
     * @return String customer ở dạng chuỗi
     */
    private String convertCustomerToString(String[] propertyCustomer) {
        StringBuilder propertyCustomerString = new StringBuilder();
        for (int i = 0; i < propertyCustomer.length; i++) {
            propertyCustomerString.append(i + ".[" + propertyCustomer[i] + "]\t");
        }
        return propertyCustomerString.toString();
    }

    /**
     * Tìm kiếm vị trí customer theo id
     * @return  vị trí của customer trong customerList, -1 nếu không tìm thấy
     */
    private int findIndexCustomerByID() {
        String id;
        id = GetService.getStr("Enter id you want to find: ");
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    private void showInfoCustomerList() {
        System.out.println("CUSTOMER_LIST");
        System.out.printf("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-12s|%-12s|\n",
                "ID", "NAME", "DAY_OF_BIRTH", "GENDER", "IDENTITY",
                "PHONE", "EMAIL", "RANK", "ADDRESS");
        String infoC = "";
        for (Customer c : customerList) {
            infoC = String.format("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-12s|%-12s|",
                    c.getId(), c.getFullName(), c.getDayOfBirth(), c.getGender(), c.getIdentity(),
                    c.getNumberPhone(), c.getEmail(), c.getRank(), c.getAddress());
            System.out.println(infoC);
        }
    }

    /**
     * Tạo một customer
     * thêm vào customerList
     * hiển thị customerList
     */
    @Override
    public void add() {
        Customer e = createCustomer();
        customerList = readFileCustomer(PATH_CUSTOMER);
        customerList.add(e);
        System.out.println();
        System.out.println("add success!");
        writeFileCustomer(PATH_CUSTOMER, customerList);
        display();
    }

    private void writeFileCustomer(String pathCustomer, List<Customer> customerList) {
        List<String> customersString = new LinkedList<>();
        for(Customer c: customerList) {
            customersString.add(c.toString());
        }
        WriteFile.writeFile(pathCustomer, customersString);
    }

    private Customer createCustomer() {
        InputPersonService.inputCustomer();
        return new Customer(InputPersonService.id,
                InputPersonService.fullName,
                InputPersonService.dayOfBirth,
                InputPersonService.gender,
                InputPersonService.identity,
                InputPersonService.numberPhone,
                InputPersonService.email,
                InputPersonService.rank,
                InputPersonService.address);
    }
}
