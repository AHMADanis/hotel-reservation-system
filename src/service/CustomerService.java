package service;


import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService{
    private static final Map<String, Customer> customers = new HashMap<>();
    private static final CustomerService SINGLETON = new CustomerService();

    private CustomerService() {}

    public static CustomerService getInstance() {
        return SINGLETON;
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

/* import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static final Map<String, Customer> customers = new HashMap<>();

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
*/