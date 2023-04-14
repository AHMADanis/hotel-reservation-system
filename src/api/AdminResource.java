package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

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
