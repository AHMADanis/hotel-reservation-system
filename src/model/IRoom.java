package model;

public interface IRoom {
    String getRoomNumber();

    default Double getRoomPrice() {
        return null;
    }
    RoomType getRoomType();
    boolean isFree();
}

