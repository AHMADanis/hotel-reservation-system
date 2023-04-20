package service;

import model.Customer;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService{
    private static final Map<String, Customer> customers = new ConcurrentHashMap<>();
    private static final CustomerService INSTANCE = new CustomerService();
    private CustomerService() {}
    public static CustomerService getInstance() {
        return INSTANCE;
    }
    public static synchronized void addCustomer(String email, String firstName, String lastName) {
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(firstName, "First name cannot be null");
        Objects.requireNonNull(lastName, "Last name cannot be null");

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
