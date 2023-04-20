package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public IRoom getRoom() {
        return room;
    }
    public void setRoom(IRoom room) {
        this.room = room;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    private static boolean isFirstRecord = true;
    @Override
    public String toString() {
        String customerName = customer.getFirstName() + " " + customer.getLastName();
        String roomNumber = room.getRoomNumber();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String checkInDateStr = dateFormat.format(checkInDate);
        String checkOutDateStr = dateFormat.format(checkOutDate);

        StringBuilder sb = new StringBuilder();
        sb.append("+------------------+-------------------+---------------------+----------------------+\n");
        if (isFirstRecord) {
            sb.append("| Customer         | Room              | Check-In Date       | Check-Out Date       |\n");
            sb.append("+------------------+-------------------+---------------------+----------------------+\n");
            isFirstRecord = false;
        }
        sb.append(String.format("| %-16s | %-17s | %-19s | %-19s  |\n", customerName, roomNumber, checkInDateStr, checkOutDateStr));
        sb.append("+------------------+-------------------+---------------------+----------------------+\n");

        return sb.toString();
    }
}
