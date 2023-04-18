package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }
    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }
    @Override
    public Double getRoomPrice() {
        return this.price;
    }
    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }
    @Override
    public boolean isFree() {
        return this.price != null && this.price.equals(0.0);
    }

    @Override
    public String toString() {
        String format = "| %-12s | %-15s | %-10s |%n";
        String divider = "+--------------+-----------------+------------+%n";
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(divider));
        sb.append(String.format(format, "Room Number", "Room Type", "Price"));
        sb.append(String.format(divider));
        sb.append(String.format(format, roomNumber, roomType, "€" + price));
        sb.append(String.format(divider));

        return sb.toString();
    }

    // need to update and under this code
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Room)) {
            return false;
        }

        final Room room = (Room) obj;
        return Objects.equals(this.roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
