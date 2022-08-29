package service.impl.get;

import models.person.Customer;
import service.IGetCustomerPropertyService;
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
}
