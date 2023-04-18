package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
public class HotelResource {
    private static final HotelResource INSTANCE = new HotelResource();
    //private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();
    private HotelResource() {}
    public static HotelResource getInstance() {
        return INSTANCE;
    }

    public Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookRoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        if(customer == null){
            throw new IllegalArgumentException("Invalid customer email");
        }
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        final Customer customer = getCustomer(customerEmail);

        if (customer == null) {
            return Collections.emptyList();
        }

        return reservationService.getCustomerReservations(getCustomer(customerEmail));
    }

    public Collection<IRoom> findAvailableRooms(final Date checkIn, final Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> findAlternativeRooms(final Date checkIn, final Date checkOut) {
        return reservationService.findAlternativeRooms(checkIn, checkOut);
    }

    public Date addDaysToDate(final Date date) {
        return reservationService.addDaysToDate(date);
    }
}
