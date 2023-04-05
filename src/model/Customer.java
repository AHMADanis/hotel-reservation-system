package model;

public class Customer {
    String firstName;
    String lastName;
    String email;
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;

        // validate email format using regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (email.matches(emailRegex)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }
    @Override
    public String toString(){
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
