package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService{
    private static final Map<String, Customer> customers = new HashMap<>();
    private static final CustomerService INSTANCE = new CustomerService();
    private CustomerService() {}
    public static CustomerService getInstance() {
        return INSTANCE;
    }
    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public static Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }
    public static Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
