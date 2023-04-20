package model;
/**
 * An enumeration of room types for a hotel reservation system.
 * Each room type is assigned a unique label (or ID) to identify it.
 */
public enum RoomType {
    SINGLE(1),
    DOUBLE(2);
    public final int id;

    RoomType(int id) {
        this.id = id;
    }

    /**
     * Returns the RoomType object that corresponds to the given id.
     * @param id the id to look up
     * @return the corresponding RoomType object
     * @throws IllegalArgumentException if no RoomType with the given id exists
     */

    public static RoomType valueOfLabel(int id) {
        for (RoomType roomType : values()) {
            if (roomType.id == id) {
                return roomType;
            }
        }
        throw new IllegalArgumentException("Invalid RoomType id:" + id);
    }
}