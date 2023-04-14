package model;
public enum RoomType {
    SINGLE(1),
    DOUBLE(2);

    public final int label;

    private RoomType(int label) {
        this.label = label;
    }

    public static RoomType valueOfLabel(int label) {
        for (RoomType roomType : values()) {
            if (roomType.label == label) {
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}