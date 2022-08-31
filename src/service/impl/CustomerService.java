package service.impl;

import models.person.Customer;
import service.ICustomerService;
import service.impl.get.GetCustomerPropertyService;
import utils.get_set_service.GetService;
import service.input.InputPersonService;
import utils.read_write.ReadFile;
import utils.read_write.WriteFile;

import java.util.LinkedList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private static CustomerService instance;

    public CustomerService() {
    }

    public synchronized static CustomerService getInstance() {
        if(instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    static final String PATH_CUSTOMER = "src/data/customer.csv";
    private List<Customer> customerList;
    private static List<Customer> readFileCustomer() {
        List<Customer> customers = new LinkedList<>();
        List<String> customersString = ReadFile.readFile(CustomerService.PATH_CUSTOMER);
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
        customerList = readFileCustomer();
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
        customerList = readFileCustomer();
        int customerIndex;
        Customer c;
        customerIndex = findIndexCustomerByID();
        if (customerIndex > -1) {
            c = customerList.get(customerIndex);
            setPropertyCustomer(c);
            writeFileCustomer(customerList);
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
        String propertyCustomerString;
        while (true) {
            propertyCustomerString = convertCustomerToString(propertyCustomer);
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
            case 0 -> {
                String id = GetService.getStr("Enter new ID: ");
                c.setId(id);
            }
            case 1 -> {
                String name = GetService.getName("Enter new full name: ");
                c.setFullName(name);
            }
            case 2 -> {
                String dayOfBirth = GetService.getDate("Enter new day of birth: ");
                c.setDayOfBirth(dayOfBirth);
            }
            case 3 -> {
                String gender = GetService.getGender();
                c.setGender(gender);
            }
            case 4 -> {
                String identity = GetService.getStr("Enter new identity: ");
                c.setIdentity(identity);
            }
            case 5 -> {
                String phoneNumber = GetService.getPhoneNumber("Enter new phone number: ");
                c.setNumberPhone(phoneNumber);
            }
            case 6 -> {
                String email = GetService.getEmail("Enter new email: ");
                c.setEmail(email);
            }
            case 7 -> {
                String rank = GetCustomerPropertyService.getRank();
                c.setRank(rank);
            }
            case 8 -> {
                String address = GetService.getStr("Enter new address:");
                c.setAddress(address);
            }
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
            propertyCustomerString.append(i).append(".[").append(propertyCustomer[i]).append("]\t");
        }
        return propertyCustomerString.toString();
    }

    /**
     * Tìm kiếm vị trí customer theo id
     * @return  vị trí của customer trong customerList, -1 nếu không tìm thấy
     */
    public int findIndexCustomerByID() {
        String id;
        id = GetService.getStr("Enter id you want to find: ");
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public Customer findCustomerBYID() {
        String id;
        customerList = readFileCustomer();
        id = GetService.getStr("Enter your choice customer id: ");
        for(Customer c : customerList) {
            if(c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
    public Customer findCustomerBYID(String customerID) {
        customerList = readFileCustomer();
        for(Customer c : customerList) {
            if(c.getId().equalsIgnoreCase(customerID)) {
                return c;
            }
        }
        return null;
    }
    private void showInfoCustomerList() {
        System.out.println("CUSTOMER_LIST");
        System.out.printf("|%-7s|%-20s|%-12s|%-7s|%-10s|%-10s|%-25s|%-12s|%-12s|\n",
                "ID", "NAME", "DAY_OF_BIRTH", "GENDER", "IDENTITY",
                "PHONE", "EMAIL", "RANK", "ADDRESS");
        String infoC;
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
        customerList = readFileCustomer();
        customerList.add(e);
        System.out.println();
        System.out.println("add success!");
        writeFileCustomer(customerList);
        display();
    }

    private void writeFileCustomer(List<Customer> customerList) {
        List<String> customersString = new LinkedList<>();
        for(Customer c: customerList) {
            customersString.add(c.toString());
        }
        WriteFile.writeFile(CustomerService.PATH_CUSTOMER, customersString);
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

    public List<Customer> getCustomerList() {
        return readFileCustomer();
    }
}
