package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
    private static final AdminResource INSTANCE = new AdminResource();
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private AdminResource() {
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(new Room("101", 100.0, RoomType.SINGLE));
        rooms.add(new Room("102", 120.0, RoomType.DOUBLE));
        rooms.add(new Room("103", 150.0, RoomType.DOUBLE));
        addRoom(rooms);
    }
    public static AdminResource getInstance() {
        return INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms) {
        for(IRoom room: rooms) {
            if(reservationService.getARoom(room.getRoomNumber())!= null){
                System.out.println("A room with the same room number already exists in the system.");
            }else {
                reservationService.addRoom(room);
            }
        }
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
