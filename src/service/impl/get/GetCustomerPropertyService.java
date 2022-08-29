package service.impl.get;

import models.person.Customer;
import service.i_get.IGetCustomerPropertyService;
import service.impl.CustomerService;
import utils.get_set_service.GetService;

import java.util.List;

public class GetCustomerPropertyService implements IGetCustomerPropertyService {
    CustomerService customerService = new CustomerService();
    private static final String CUSTOMER_ID_REGEX = "C\\d{3,}";
    @Override
    public String getCustomerID() {
        String customerID;
        while (true) {
            customerID = GetService.getStr("Enter customer id in the format [Cxxx]: ");
            if(!checkCustomerId(customerID)){
                System.out.println("Customer id used!");
            } else if(!customerID.matches(CUSTOMER_ID_REGEX)) {
                System.out.println("Example: C001, C002, C003");
            } else {
                return customerID;
            }
        }

    }
    private boolean checkCustomerId(String customerID) {
        List<Customer> customers = customerService.getCustomerList();
        for(Customer c : customers) {
            if(c.getId().equalsIgnoreCase(customerID)) {
                return false;
            }
        }
        return true;
    }
    public static String getChoiceCustomerID() {
        CustomerService customerService = new CustomerService();
        customerService.display();
        Customer customer;
        while (true) {
            customer = customerService.findCustomerBYID();
            if(customer != null) {
                return  customer.getId();
            }
        }
    }
    public static String getRank() {
        String[] positions = {"Diamond", "Platinum", "Gold", "Silver", "Member"};
        int choose;
        System.out.println("1. Diamond \t2. Platinum \t3. Gold \t4. Silver \t5. Member");
        choose = GetService.getNumberInteger("Your choose: ", 1, 5);
        return positions[choose - 1];
    }
}
