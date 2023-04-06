package model;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        // Validate email using regex
        if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Override toString() method to provide a better description of the Customer object
    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + ", Email: " + email;
    }
}

