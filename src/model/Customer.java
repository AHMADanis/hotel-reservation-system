package model;

import java.util.regex.Pattern;
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
    private static boolean isFirstRecord = true;
    @Override
    public String toString() {
        String format = "| %-20s | %-30s |%n";
        StringBuilder sb = new StringBuilder();
        if (isFirstRecord) {
            sb.append("+----------------------+--------------------------------+\n");
            sb.append(String.format(format, "Name", "Email"));
            sb.append("+----------------------+--------------------------------+\n");
            isFirstRecord = false;
        }
        sb.append(String.format(format, firstName + " " + lastName, email));
        return sb.toString();
    }

}

