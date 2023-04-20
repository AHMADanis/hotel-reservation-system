package model;

public class FreeRoom extends Room {
    private boolean isFree;
    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }
    public boolean isFree() {
        return isFree;
    }
    public void setFree(boolean free) {
        isFree = free;
    }
    @Override
    public String toString(){
        return "Free Room: \n" + super.toString();
    }
}
