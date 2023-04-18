import api.HotelResource;

import model.Reservation;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
    private static final HotelResource hotelResource = HotelResource.getInstance();
    public static void adminMenuOptions() {
        String[] options = {"Find and reserve a room", "See my reservations", "Create an Account", "Admin", "Exit"};
        StringBuilder menu = new StringBuilder();
        menu.append("\nThank you for using our hotel reservation application\n");
        menu.append("--------------------------------------------\n");
        for (int i = 0; i < options.length; i++) {
            menu.append(String.format("%d. %s\n", i+1, options[i]));
        }
        menu.append("--------------------------------------------\n");
        menu.append("Please enter the number corresponding to your desired menu option:\n");
        System.out.print(menu);
    }
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String[] options = {"1", "2", "3", "4", "5"};
        boolean exit = false;

        while (!exit) {
            adminMenuOptions();
            String input = scanner.nextLine().trim();

            if (Arrays.asList(options).contains(input)) {
                switch (input) {
                    case "1" -> findAndReserveRoom();
                    case "2" -> seeMyReservation();
                    case "3" -> createAccount();
                    case "4" -> AdminMenu.adminMenu();
                    case "5" -> {
                        System.out.println("Exit");
                        exit = true;
                    }
                    default -> System.out.println("Unknown action\n");
                }
            } else {
                System.out.println("Invalid input. Please select a number from 1 to 5\n");
            }
        }
    }
    private static void findAndReserveRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkIn = getInputDate(scanner);

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getInputDate(scanner);

        if (checkIn != null && checkOut != null) {
            Collection<IRoom> availableRooms = hotelResource.findAvailableRooms(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                Collection<IRoom> alternativeRooms = hotelResource.findAlternativeRooms(checkIn, checkOut);

                if (alternativeRooms.isEmpty()) {
                    System.out.println("No rooms found.");
                } else {
                    final Date alternativeCheckIn = hotelResource.addDaysToDate(checkIn);
                    final Date alternativeCheckOut = hotelResource.addDaysToDate(checkOut);
                    System.out.println("We've only found rooms on alternative dates:" +
                            "\nCheck-In Date:" + alternativeCheckIn +
                            "\nCheck-Out Date:" + alternativeCheckOut);

                    printRooms(alternativeRooms);
                    reserveRoom(scanner, alternativeCheckIn, alternativeCheckOut, alternativeRooms);
                }
            } else {
                printRooms(availableRooms);
                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }
    }

    private static Date getInputDate(final Scanner scanner) {
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            findAndReserveRoom();
        }

        return null;
    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate,
                                    final Date checkOutDate, final Collection<IRoom> rooms) {
        System.out.println("Would you like to book? y/n");
        final String bookRoom = scanner.nextLine();

        if ("y".equals(bookRoom)) {
            System.out.println("Do you have an account with us? y/n");
            final String haveAccount = scanner.nextLine();

            if ("y".equals(haveAccount)) {
                System.out.println("Enter Email format: name@domain.com");
                final String customerEmail = scanner.nextLine();

                if (hotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("Customer not found.\nYou may need to create a new account.");
                } else {
                    System.out.println("What room number would you like to reserve?");
                    final String roomNumber = scanner.nextLine();

                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        final IRoom room = hotelResource.getRoom(roomNumber);

                        final Reservation reservation = hotelResource
                                .bookRoom(customerEmail, room, checkInDate, checkOutDate);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: room number not available.\nStart reservation again.");
                    }
                }

                adminMenuOptions();
            } else {
                System.out.println("Please, create an account.");
                adminMenuOptions();
            }
        } else if ("n".equals(bookRoom)){
            adminMenuOptions();
        } else {
            reserveRoom(scanner, checkInDate, checkOutDate, rooms);
        }
    }

    private static void printRooms(final Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void seeMyReservation() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Email format: name@domain.com");
        final String customerEmail = scanner.nextLine();

        printReservations(hotelResource.getCustomersReservations(customerEmail));
    }

    private static void printReservations(final Collection<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(reservation -> System.out.println("\n" + reservation));
        }
    }

    private static void createAccount() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Email format: name@domain.com");
        final String email = scanner.nextLine();

        System.out.println("First Name:");
        final String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        final String lastName = scanner.nextLine();

        try {
            hotelResource.createCustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");

            adminMenuOptions();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
    }

}