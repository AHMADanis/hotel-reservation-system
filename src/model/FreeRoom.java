package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType); // Set the price to 0
    }

    @Override
    public String toString(){
        //return "FreeRoom " + getRoomNumber() + ", " + getRoomType() + ", " + "Price: " + getRoomPrice();
        return "Free Room: " + super.toString();
    }
}
