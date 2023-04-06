package model;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;
    private boolean isFree;
    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = true;
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
        return roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    // Setter method for the 'isFree' field
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Room Type: " + roomType + ", Price: $" + price + ", Free: " + isFree;
    }
}
