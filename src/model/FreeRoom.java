package model;

public class FreeRoom extends Room {
    private boolean isFree;
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType); // Set the price to 0
    }
    public boolean isFree() {
        return isFree;
    }
    public void setFree(boolean free) {
        isFree = free;
    }
    @Override
    public String toString(){
        //return "FreeRoom " + getRoomNumber() + ", " + getRoomType() + ", " + "Price: " + getRoomPrice();
        return "Free Room: " + super.toString();
    }
}
