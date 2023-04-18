package api;

import java.util.Collection;
import java.util.List;
import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
    private static final AdminResource INSTANCE = new AdminResource();
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private AdminResource() {
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
    }

    public static AdminResource getInstance() {
        return INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
/*
public class AdminResource {

    // Static reference
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final RoomService roomService = RoomService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    // Get customer by email
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    // Add list of rooms
    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            roomService.addRoom(room);
        }
    }

    // Get all rooms
    public Collection<IRoom> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get all customers
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Display all reservations
    public void displayAllReservations() {
        Collection<Reservation> reservations = reservationService.getAllReservations();
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}

 */
