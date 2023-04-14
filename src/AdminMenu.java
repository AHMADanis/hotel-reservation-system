import java.util.Scanner;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void display() {
        int choice = 0;
        do {
            System.out.println("\nAdmin Menu");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Please select an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Call method to see all customers
                    break;
                case 2:
                    // Call method to see all rooms
                    break;
                case 3:
                    // Call method to see all reservations
                    break;
                case 4:
                    // Call method to add a room
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }
}
