package model;

public class Room implements IRoom {
    //implement Iroom interface and its methods  ***
    String roomNumber;
    Double price;
    RoomType enumeration;
    boolean isFree;
    public Room(String roomNumber, Double price, RoomType enumeration, boolean isFree){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.isFree = isFree;
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
        return false;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber + ", Room type: " + enumeration + ", Room price: " + price + ", Is room free: " + isFree;
    }
}
