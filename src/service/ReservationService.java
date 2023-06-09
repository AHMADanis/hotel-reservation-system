package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private static final ReservationService INSTANCE = new ReservationService();
    private static final int RECOMMENDED_DAYS = 7;
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

        Collection<Reservation> customerReservations = getOrCreateCustomerReservations(customer);
        customerReservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(final Date checkInDate, final Date checkOutDate) {
        return findAvailableRooms(checkInDate, checkOutDate);
    }

    public Collection<IRoom> findAltRooms(final Date checkInDate, final Date checkOutDate) {
        return findAvailableRooms(addDaysToDate(checkInDate), addDaysToDate(checkOutDate));
    }

    private Collection<IRoom> findAvailableRooms(final Date checkInDate, final Date checkOutDate) {
        final Collection<Reservation> allReservations = getAllReservations();
        final Collection<IRoom> notAvailableRooms = new LinkedList<>();

        for (Reservation reservation : allReservations) {
            if (isReservationOverlappingDateRange(reservation, checkInDate, checkOutDate)) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }

        return rooms.values()
                .stream()
                .filter(room -> notAvailableRooms.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }

    public Date addDaysToDate(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, RECOMMENDED_DAYS);
        return calendar.getTime();
    }

    private boolean isReservationOverlappingDateRange(final Reservation reservation, final Date checkInDate,
                                                      final Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

    public Collection<Reservation> getCustomerReservations(final Customer customer) {
        return getOrCreateCustomerReservations(customer);
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
        return reservations.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Collection<Reservation> getOrCreateCustomerReservations(Customer customer) {
        return reservations.computeIfAbsent(customer.getEmail(), k -> new LinkedList<>());
    }
}
