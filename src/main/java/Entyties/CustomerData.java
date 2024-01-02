package Entyties;

import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    private static List<Customer> customerList = new ArrayList<>();

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void addCustomer(Customer customer) {
        customerList.add(customer);
    }

}
