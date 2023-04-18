package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static final ReservationService INSTANCE = new ReservationService();
    private static final int DEFAULT_RECOMMENDED_DAYS_FOR_ROOMS = 7;
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();
    private ReservationService() {}
    public static ReservationService getInstance() {
        return INSTANCE;
    }
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }
    public IRoom getARoom(final String roomNumber) {
        return rooms.get(roomNumber);
    }
    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
         Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        Collection<Reservation> customerReservations = getCustomerReservations(customer);

        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }

        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);

        return reservation;
    }
    public Collection<IRoom> findRooms(final Date checkInDate, final Date checkOutDate) {
        return findAvailableRooms(checkInDate, checkOutDate);
    }
    public Collection<IRoom> findAlternativeRooms(final Date checkInDate, final Date checkOutDate) {
        return findAvailableRooms(addDaysToDate(checkInDate), addDaysToDate(checkOutDate));
    }
    private Collection<IRoom> findAvailableRooms(final Date checkInDate, final Date checkOutDate) {
        final Collection<Reservation> allReservations = getAllReservations();
        final Collection<IRoom> notAvailableRooms = new LinkedList<>();

        for (Reservation reservation : allReservations) {
            if (reservationOverlaps(reservation, checkInDate, checkOutDate)) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }
        return rooms.values().stream().filter(room -> notAvailableRooms.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }

    public Date addDaysToDate(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, DEFAULT_RECOMMENDED_DAYS_FOR_ROOMS);
        return calendar.getTime();
    }

    private boolean reservationOverlaps(final Reservation reservation, final Date checkInDate,
                                        final Date checkOutDate){
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

    public Collection<Reservation> getCustomerReservations(final Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        final Collection<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
        }
    }
    private Collection<Reservation> getAllReservations() {
        final Collection<Reservation> allReservations = new LinkedList<>();

        for(Collection<Reservation> reservations : reservations.values()) {
            allReservations.addAll(reservations);
        }
        return allReservations;
    }
}
/* import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private static final List<Reservation> reservations = new ArrayList<>();
    private static final List<IRoom> rooms = new ArrayList<>();

    public static void addRoom(IRoom room) {
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkoutDate);
        reservations.add(reservation);
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkoutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : rooms) {
            if (room.isFree()) {
                availableRooms.add(room);
            } else {
                Collection<Reservation> reservations = getReservations(room);
                boolean conflicts = false;
                for (Reservation reservation : reservations) {
                    if (checkInDate.before(reservation.getCheckoutDate()) && checkoutDate.after(reservation.getCheckInDate())) {
                        conflicts = true;
                        break;
                    }
                }
                if (!conflicts) {
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public static void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private static Collection<Reservation> getReservations(IRoom room) {
        Collection<Reservation> roomReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                roomReservations.add(reservation);
            }
        }
        return roomReservations;
    }

}

 */
