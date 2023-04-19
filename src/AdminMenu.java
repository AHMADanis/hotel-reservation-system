import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static void adminScreenOptions() {
        String[] menuOptions = {
                "See all Customers",
                "See all Rooms",
                "See all Reservations",
                "Add a Room",
                "Back to Main Menu"};
        String divider = "--------------------------------------------";
        System.out.println("\033[1mADMIN MENU\033[0m");
        System.out.println(divider);
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i+1) + ". " + menuOptions[i]);
        }
        System.out.println(divider);
        System.out.println("Please enter your desired menu option:");
    }
    public static void adminMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        adminScreenOptions();
        while (true) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("No input received. Please try again.");
                continue;
            }
            char option = line.charAt(0);
            switch (option) {
                case '1' -> seeAllCustomers();
                case '2' -> seeAllRooms();
                case '3' -> seeAllReservations();
                case '4' -> addRoom();
                case '5' -> MainMenu.mainMenuOptions();
                default -> System.out.println("Invalid option selected. Please try again.");
            }
            if (option == '5') {
                break;
            }
        }
    }
    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers were found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms were found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide the room number:");
        final String roomNumber = scanner.nextLine();

        System.out.println("Please provide the per night price:");
        final double roomPrice = enterRoomPrice(scanner);

        System.out.println("Please select the room size: 1 -> SINGLE, 2 -> DOUBLE:");
        final RoomType roomType = enterRoomType(scanner);

        final Room room = new Room(roomNumber, roomPrice, roomType);

        adminResource.addRoom(Collections.singletonList(room));
        System.out.println("The room has been added successfully!");
        addAnotherRoom();
    }

    private static double enterRoomPrice(final Scanner scanner) {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("Please enter a valid price for the room!");
            return enterRoomPrice(scanner);
        }
    }

    private static RoomType enterRoomType(final Scanner scanner) {
        try {
            return RoomType.valueOfLabel(Integer.parseInt(scanner.nextLine()));
        } catch (IllegalArgumentException exp) {
            System.out.println("Select a valid option:  1 -> [ single bed ] or 2 -> [ double bed ]");
            return enterRoomType(scanner);
        }
    }

    private static void addAnotherRoom() {
        final Scanner scanner = new Scanner(System.in);
        String anotherRoom;

        do {
            System.out.println("Add another room? (Y/N)");
            anotherRoom = scanner.nextLine().toUpperCase();
        } while (!anotherRoom.equals("Y") && !anotherRoom.equals("N"));
        switch (anotherRoom) {
            case "Y" -> addRoom();
            case "N" -> adminScreenOptions();
            default -> {
                System.out.println("Invalid input. Please enter Y or N.");
                addAnotherRoom();
            }
        }
    }
}