import api.HotelResource;

import model.Customer;
import model.Reservation;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainMenu {
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final HotelResource hotelResource = HotelResource.getInstance();
    public static void mainMenuOptions() {
        String[] options = {"Find and reserve a room", "See my reservations", "Create an Account", "Admin", "Exit"};
        StringBuilder menu = new StringBuilder();
        menu.append("\033[1mUDACITY HOTEL RESERVATION SYSTEM\033[0m\n");
        menu.append("--------------------------------------------\n");
        for (int i = 0; i < options.length; i++) {
            menu.append(String.format("%d. %s\n", i+1, options[i]));
        }
        menu.append("--------------------------------------------\n");
        menu.append("Please choose an option from the menu.\n");
        System.out.print(menu);
    }
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String[] options = {"1", "2", "3", "4", "5"};
        boolean exit = false;
        while (!exit) {
            mainMenuOptions();
            String input = scanner.nextLine().trim();

            if (Arrays.asList(options).contains(input)) {
                switch (input) {
                    case "1" -> findAndReserveRoom();
                    case "2" -> seeMyReservation();
                    case "3" -> createAccount();
                    case "4" -> AdminMenu.adminMenuOptions();
                    case "5" -> {
                        System.out.println("Exit");
                        exit = true;
                    }
                    default -> System.out.println("Unknown action\n");
                }
            } else {
                System.out.println("Please choose a number between 1 and 5\n");
            }
        }
    }
    private static void findAndReserveRoom() {
        Date checkIn = getReservationDate("Check-In");
        Date checkOut = getReservationDate("Check-Out");
        final Scanner scanner = new Scanner(System.in);

        if (checkIn != null && checkOut != null) {
            Collection<IRoom> availableRooms = hotelResource.findAvailableRooms(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                Collection<IRoom> alternativeRooms = hotelResource.findAlternativeRooms(checkIn, checkOut);

                if (alternativeRooms.isEmpty()) {
                    System.out.println("Error: Room not available.");
                } else {
                    Date alternativeCheckIn = hotelResource.addDaysToDate(checkIn);
                    Date alternativeCheckOut = hotelResource.addDaysToDate(checkOut);
                    System.out.println("Unfortunately, these rooms are only available on different dates.");
                    printReservationDate("Check-In", alternativeCheckIn);
                    printReservationDate("Check-Out", alternativeCheckOut);
                    printRooms(alternativeRooms);
                    reserveRoom(scanner, alternativeCheckIn, alternativeCheckOut, alternativeRooms);
                }
            } else {
                printRooms(availableRooms);
                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }
    }

    private static Date getReservationDate(String dateType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + dateType + " Date mm/dd/yyyy");
        return getInputDate(scanner);
    }

    private static void printReservationDate(String dateType, Date date) {
        System.out.println(dateType + " Date: " + date);
    }


    private static Date getInputDate(final Scanner scanner) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            findAndReserveRoom();
        }
        return null;
    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate, final Date checkOutDate, final Collection<IRoom> rooms) {
        String prompt = "Do you want to make a reservation? (y/n)";
        if (promptUser(prompt, scanner)) {
            mainMenuOptions();
            return;
        }
        prompt = "Have you registered with us previously? (y/n)";
        if (promptUser(prompt, scanner)) {
            System.out.println("You need to create an account to proceed.");
            mainMenuOptions();
            return;
        }
        prompt = "Enter your email (format: name@domain.com).";
        String customerEmail = getStringInput(prompt, scanner);
        Customer customer = hotelResource.getCustomer(customerEmail);
        if (customer == null) {
            System.out.println("Account not found for the entered email. Please create a new account.");
            createAccount();
            return;
        }
        prompt = "Please specify the room number for the reservation.";
        String roomNumber = getStringInput(prompt, scanner);
        IRoom room = hotelResource.getRoom(roomNumber);
        if (!rooms.contains(room)) {
            System.out.println("Room unavailable. Please try again.");
            mainMenuOptions();
            return;
        }
        Reservation reservation = hotelResource.bookRoom(customerEmail, room, checkInDate, checkOutDate);
        System.out.println("The reservation has been successfully created! \n" + reservation);
    }


    private static boolean promptUser(String prompt, Scanner scanner) {
        System.out.println(prompt);
        String userInput = scanner.nextLine();
        while (!userInput.matches("[yn]")) {
            System.out.println("System only accept 'y' or 'n' as input. Please try again.");
            System.out.println(prompt);
            userInput = scanner.nextLine();
        }
        return !userInput.equals("y");
    }

    private static String getStringInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        return scanner.nextLine();
    }


    private static void printRooms(final Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void seeMyReservation() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Email (format: name@domain.com)");
        final String customerEmail = scanner.nextLine();

        displayReservations(hotelResource.getCustomersReservations(customerEmail));
    }
    private static void displayReservations(Collection<Reservation> reservations) {
        try {
            String output = reservations.stream()
                    .map(reservation -> "\n" + reservation)
                    .collect(Collectors.joining());

            System.out.println(reservations.isEmpty() ?
                    "Empty email" : output);
        } catch (Exception e) {
            System.out.println("There are no reservation against your email");
        }
    }

    private static void createAccount() {
        final Scanner scanner = new Scanner(System.in);
        String[] prompts = {"Enter your email (format: name@domain.com)", "First Name:", "Last Name:"};
        String[] inputs = new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.println(prompts[i]);
            inputs[i] = scanner.nextLine();
        }
        try {
            hotelResource.createCustomer(inputs[0], inputs[1], inputs[2]);
            System.out.println("Account created successfully!");
            mainMenuOptions();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
    }

}