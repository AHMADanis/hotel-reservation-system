package service;

import model.Customer;
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
