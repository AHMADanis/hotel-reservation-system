package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }
    @Override
    public Double getRoomPrice() {
        return price;
    }
    @Override
    public RoomType getRoomType() {
        return enumeration;
    }
    @Override
    public boolean isFree() {
        return price != null && price.equals(0.0);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Room otherRoom)) {
            return false;
        }
        return Objects.equals(roomNumber, otherRoom.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
    private static boolean isFirstRecord = true;
    @Override
    public String toString() {
        String format = "| %-12s | %-15s | %-10s |";
        String divider = "+--------------+-----------------+------------+";
        StringBuilder sb = new StringBuilder();
        if(isFirstRecord) {
            sb.append(divider);
            sb.append(String.format("\n" + format, "Room Number", "Room Type", "Price"));
            sb.append("\n").append(divider);
            isFirstRecord = false;
        } else {
            sb.append(divider);
        }
        sb.append(String.format("\n" + format, roomNumber, enumeration, "€" + price));
        return sb.toString();
    }
}
