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
        }
        sb.append(String.format("\n" + format, roomNumber, roomType, "€" + price));
        return sb.toString();
    }

    // updated override
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Room otherRoom)) {
            return false;
        }
        return this.roomNumber.equals(otherRoom.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
