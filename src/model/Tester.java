package model;

import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("Anees", "Ahmad", "anees.ahmad1107@gmail.com");
        System.out.println(customer);

        // Create a Free Room
        FreeRoom freeRoom = new FreeRoom("101", RoomType.SINGLE);
        System.out.println(freeRoom);

        // Create a room
        IRoom room = new Room("101", 50.0, RoomType.SINGLE);

        // Create a reservation
        Date checkInDate = new Date();
        Date checkOutDate = new Date(checkInDate.getTime() + 86400000); // add one day (in milliseconds)
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        System.out.println(reservation);


    }
}
