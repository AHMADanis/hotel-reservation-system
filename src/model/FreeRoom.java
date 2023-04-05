package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType enumeration, boolean isFree) {
        super(roomNumber, 0.0, enumeration, isFree);
    }

    @Override
    public String toString(){
        return "FreeRoom " + getRoomNumber() + ", " + getRoomType() + ", " + "Price: " + getRoomPrice();
    }
}
