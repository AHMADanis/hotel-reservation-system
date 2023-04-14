import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        System.out.println("Welcome to the Hotel Reservation System!\n");
        System.out.println("MAIN MENU");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit\n");
        System.out.print("Please select an option: ");
        String input = scanner.nextLine();
        handleInput(input);
    }

    private void handleInput(String input) {
        switch(input) {
            case "1":
                // go to room reservation menu
                break;
            case "2":
                // go to my reservations menu
                break;
            case "3":
                // go to account creation menu
                break;
            case "4":
                // go to admin menu
                break;
            case "5":
                System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please enter a number from 1 to 5.");
                display();
                break;
        }
    }
}
